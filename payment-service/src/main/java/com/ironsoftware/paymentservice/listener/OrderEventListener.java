package com.ironsoftware.paymentservice.listener;

import com.ironsoftware.common.events.order.OrderCreatedEvent;
import com.ironsoftware.paymentservice.dto.PaymentRequest;
import com.ironsoftware.paymentservice.model.PaymentMethod;
import com.ironsoftware.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderEventListener {
    
    private final PaymentService paymentService;

    @KafkaListener(topics = "order-created", groupId = "payment-service-group")
    public void handleOrderCreated(OrderCreatedEvent event, Acknowledgment ack) {
        try {
            log.info("Received order created event for payment processing: {}", event.getOrderId());
            
            // Create payment request from order event
            PaymentRequest paymentRequest = PaymentRequest.builder()
                    .orderId(event.getOrderId())
                    .userId(event.getUserId())
                    .amount(event.getTotalAmount())
                    .paymentMethod(PaymentMethod.CREDIT_CARD) // Default payment method
                    .build();
            
            // Process payment asynchronously
            paymentService.processPayment(paymentRequest);
            
            log.info("Payment processing initiated for order: {}", event.getOrderId());
            ack.acknowledge();
        } catch (Exception e) {
            log.error("Error processing order created event for payment: {}", event.getOrderId(), e);
            // In a real implementation, you might want to send to DLQ or retry
        }
    }
}