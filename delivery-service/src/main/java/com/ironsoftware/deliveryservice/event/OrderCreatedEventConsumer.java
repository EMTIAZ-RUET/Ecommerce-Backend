package com.ironsoftware.deliveryservice.event;

import com.ironsoftware.common.events.order.OrderCreatedEvent;
import com.ironsoftware.deliveryservice.entity.DeliveryAddress;
import com.ironsoftware.deliveryservice.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderCreatedEventConsumer {

    private final DeliveryService deliveryService;

    @KafkaListener(topics = "order-created", groupId = "delivery-service-group")
    public void consume(OrderCreatedEvent event) {
        log.info("Received order created event: {}", event);
        
        try {
            // Create delivery address from shipping address in order
            DeliveryAddress deliveryAddress = DeliveryAddress.builder()
                    .recipientName("Customer") // Default value since common event doesn't have detailed address
                    .street(event.getShippingAddress())
                    .city("Default City")
                    .state("Default State")
                    .country("Default Country")
                    .zipCode("00000")
                    .phoneNumber("000-000-0000")
                    .build();
            
            // For simplicity, we're using default pickup location (warehouse) coordinates
            double pickupLongitude = 0.0; // Replace with actual warehouse longitude
            double pickupLatitude = 0.0;  // Replace with actual warehouse latitude
            
            // For delivery location, we would typically geocode the shipping address
            // For simplicity, we're using default coordinates
            double deliveryLongitude = 0.0; // Replace with geocoded longitude
            double deliveryLatitude = 0.0;  // Replace with geocoded latitude
            
            // Create delivery for the order
            deliveryService.createDelivery(
                    event.getOrderId(),
                    event.getUserId(),
                    deliveryAddress,
                    pickupLongitude,
                    pickupLatitude,
                    deliveryLongitude,
                    deliveryLatitude
            );
            
            log.info("Created delivery for order: {}", event.getOrderId());
        } catch (Exception e) {
            log.error("Error processing order created event: {}", e.getMessage(), e);
        }
    }
}