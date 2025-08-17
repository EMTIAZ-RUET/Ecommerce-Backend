package com.ironsoftware.orderservice.listener;

import com.ironsoftware.common.events.inventory.InventoryUpdatedEvent;
import com.ironsoftware.common.events.payment.PaymentProcessedEvent;
import com.ironsoftware.orderservice.saga.OrderSaga;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEventListener {
    
    private final OrderSaga orderSaga;

    @KafkaListener(topics = "inventory-confirmed", groupId = "${spring.kafka.consumer.group-id}")
    public void handleInventoryConfirmed(InventoryUpdatedEvent event, Acknowledgment ack) {
        try {
            orderSaga.handleInventoryConfirmed(event.getOrderId());
            ack.acknowledge();
        } catch (Exception e) {
            // Log error and potentially retry or move to DLQ
        }
    }

    @KafkaListener(topics = "payment-processed", groupId = "${spring.kafka.consumer.group-id}")
    public void handlePaymentProcessed(PaymentProcessedEvent event, Acknowledgment ack) {
        try {
            // Handle payment completion and update order status
            ack.acknowledge();
        } catch (Exception e) {
            // Log error and potentially retry or move to DLQ
        }
    }
}
