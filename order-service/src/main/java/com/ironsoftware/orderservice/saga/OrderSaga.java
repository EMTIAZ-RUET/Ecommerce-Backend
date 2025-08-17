package com.ironsoftware.orderservice.saga;

import com.ironsoftware.common.events.inventory.InventoryUpdatedEvent;
import com.ironsoftware.common.events.order.OrderCreatedEvent;
import com.ironsoftware.common.events.payment.PaymentProcessedEvent;
import com.ironsoftware.common.kafka.EventPublisher;
import com.ironsoftware.common.saga.SagaInstance;
import com.ironsoftware.common.saga.SagaInstanceRepository;
import com.ironsoftware.common.saga.SagaState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderSaga {
    private final EventPublisher eventPublisher;
    private final SagaInstanceRepository sagaRepository;

    public void startOrderSaga(OrderCreatedEvent orderCreatedEvent) {
        String sagaId = UUID.randomUUID().toString();
        
        // Initialize Saga State
        SagaState sagaState = SagaState.builder()
            .sagaId(sagaId)
            .orderId(orderCreatedEvent.getOrderId())
            .status(SagaState.SagaStatus.STARTED)
            .stepStatus(new HashMap<>())
            .currentStep("INVENTORY_CHECK")
            .startTime(Instant.now())
            .build();

        // Create and save Saga Instance
        SagaInstance sagaInstance = new SagaInstance();
        sagaInstance.setSagaId(sagaId);
        sagaInstance.setSagaType("ORDER_SAGA");
        sagaInstance.setSagaState(sagaState);
        sagaInstance.setCreatedAt(Instant.now());
        sagaInstance.setUpdatedAt(Instant.now());
        sagaRepository.save(sagaInstance);

        // Publish event to check inventory
        InventoryUpdatedEvent inventoryEvent = new InventoryUpdatedEvent();
        inventoryEvent.setEventId(UUID.randomUUID().toString());
        inventoryEvent.setOrderId(orderCreatedEvent.getOrderId());
        inventoryEvent.setTimestamp(Instant.now());
        // Set other necessary fields...

        eventPublisher.publishEvent("inventory-check", orderCreatedEvent.getOrderId(), inventoryEvent)
            .thenAccept(result -> updateSagaState(sagaId, "INVENTORY_CHECK", SagaState.StepStatus.COMPLETED))
            .exceptionally(ex -> {
                handleSagaError(sagaId, "INVENTORY_CHECK", ex.getMessage());
                return null;
            });
    }

    public void handleInventoryConfirmed(String orderId) {
        SagaInstance saga = sagaRepository.findBySagaState_OrderId(orderId);
        if (saga == null) return;

        // Update saga state and move to payment processing
        saga.getSagaState().setCurrentStep("PAYMENT_PROCESSING");
        saga.setUpdatedAt(Instant.now());
        sagaRepository.save(saga);

        // Trigger payment processing
        PaymentProcessedEvent paymentEvent = new PaymentProcessedEvent();
        // Set payment event fields...

        eventPublisher.publishEvent("payment-processing", orderId, paymentEvent)
            .thenAccept(result -> updateSagaState(saga.getSagaId(), "PAYMENT_PROCESSING", SagaState.StepStatus.COMPLETED))
            .exceptionally(ex -> {
                handleSagaError(saga.getSagaId(), "PAYMENT_PROCESSING", ex.getMessage());
                return null;
            });
    }

    private void updateSagaState(String sagaId, String step, SagaState.StepStatus status) {
        SagaInstance saga = sagaRepository.findBySagaId(sagaId);
        if (saga == null) return;

        saga.getSagaState().getStepStatus().put(step, status);
        saga.setUpdatedAt(Instant.now());

        if (isAllStepsCompleted(saga)) {
            saga.getSagaState().setStatus(SagaState.SagaStatus.COMPLETED);
            saga.getSagaState().setEndTime(Instant.now());
        }

        sagaRepository.save(saga);
    }

    private void handleSagaError(String sagaId, String step, String errorMessage) {
        SagaInstance saga = sagaRepository.findBySagaId(sagaId);
        if (saga == null) return;

        saga.getSagaState().setStatus(SagaState.SagaStatus.FAILED);
        saga.getSagaState().setErrorMessage(errorMessage);
        saga.getSagaState().getStepStatus().put(step, SagaState.StepStatus.FAILED);
        saga.setUpdatedAt(Instant.now());

        // Start compensation logic
        startCompensation(saga);

        sagaRepository.save(saga);
    }

    private void startCompensation(SagaInstance saga) {
        saga.getSagaState().setStatus(SagaState.SagaStatus.COMPENSATING);
        // Implement compensation logic for each completed step in reverse order
        // For example, refund payment, restore inventory, etc.
    }

    private boolean isAllStepsCompleted(SagaInstance saga) {
        return saga.getSagaState().getStepStatus().values()
            .stream()
            .allMatch(status -> status == SagaState.StepStatus.COMPLETED);
    }
}
