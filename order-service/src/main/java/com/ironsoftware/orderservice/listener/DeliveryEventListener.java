package com.ironsoftware.orderservice.listener;

import com.ironsoftware.common.events.delivery.DeliveryStatusUpdatedEvent;
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
public class DeliveryEventListener {
    
    private final OrderRepository orderRepository;

    @KafkaListener(topics = "delivery-status-updated", groupId = "${spring.kafka.consumer.group-id}")
    public void handleDeliveryStatusUpdated(DeliveryStatusUpdatedEvent event, Acknowledgment ack) {
        try {
            log.info("Received delivery status update for order: {}, status: {}", 
                    event.getOrderId(), event.getStatus());
            
            orderRepository.findById(event.getOrderId())
                .ifPresent(order -> {
                    switch (event.getStatus()) {
                        case "PICKED_UP":
                        case "IN_TRANSIT":
                            order.setStatus(OrderStatus.SHIPPING);
                            log.info("Order {} status updated to SHIPPING", event.getOrderId());
                            break;
                        case "DELIVERED":
                            order.setStatus(OrderStatus.DELIVERED);
                            log.info("Order {} marked as DELIVERED", event.getOrderId());
                            break;
                        case "FAILED":
                        case "CANCELLED":
                            // Keep current status or handle as needed
                            log.info("Delivery failed/cancelled for order {}", event.getOrderId());
                            break;
                        default:
                            log.info("Unhandled delivery status: {} for order {}", 
                                    event.getStatus(), event.getOrderId());
                    }
                    orderRepository.save(order);
                });
            
            ack.acknowledge();
        } catch (Exception e) {
            log.error("Error processing delivery status update for order: {}", event.getOrderId(), e);
        }
    }
}