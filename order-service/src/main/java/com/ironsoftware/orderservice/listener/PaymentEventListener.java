package com.ironsoftware.orderservice.listener;

import com.ironsoftware.common.events.payment.PaymentProcessedEvent;
import com.ironsoftware.orderservice.entity.OrderStatus;
import com.ironsoftware.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentEventListener {
    
    private final OrderRepository orderRepository;

    @KafkaListener(topics = "payment-events", groupId = "${spring.kafka.consumer.group-id}")
    public void handlePaymentProcessed(PaymentProcessedEvent event, Acknowledgment ack) {
        try {
            log.info("Received payment processed event for order: {}", event.getOrderId());
            
            orderRepository.findById(event.getOrderId())
                .ifPresent(order -> {
                    if ("COMPLETED".equals(event.getStatus())) {
                        order.setStatus(OrderStatus.PAID);
                        log.info("Order {} marked as paid after successful payment", event.getOrderId());
                    } else if ("FAILED".equals(event.getStatus())) {
                        order.setStatus(OrderStatus.CANCELLED);
                        log.info("Order {} cancelled due to payment failure", event.getOrderId());
                    }
                    orderRepository.save(order);
                });
            
            ack.acknowledge();
        } catch (Exception e) {
            log.error("Error processing payment event for order: {}", event.getOrderId(), e);
            // In a real implementation, you might want to send to DLQ or retry
        }
    }
}