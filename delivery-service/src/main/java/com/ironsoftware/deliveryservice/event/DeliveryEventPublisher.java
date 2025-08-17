package com.ironsoftware.deliveryservice.event;

import com.ironsoftware.common.events.delivery.DeliveryCreatedEvent;
import com.ironsoftware.common.events.delivery.DeliveryStatusUpdatedEvent;
import com.ironsoftware.deliveryservice.entity.Delivery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeliveryEventPublisher {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    
    private static final String DELIVERY_CREATED_TOPIC = "delivery-created";
    private static final String DELIVERY_STATUS_UPDATED_TOPIC = "delivery-status-updated";
    
    public void publishDeliveryCreatedEvent(Delivery delivery) {
        DeliveryCreatedEvent event = new DeliveryCreatedEvent();
        event.setEventId(UUID.randomUUID().toString());
        event.setEventType("DELIVERY_CREATED");
        event.setTimestamp(Instant.now());
        event.setSource("delivery-service");
        event.setVersion("1.0");
        
        event.setDeliveryId(delivery.getId().toString());
        event.setTrackingNumber(delivery.getTrackingNumber());
        event.setOrderId(delivery.getOrderId());
        event.setCustomerId(delivery.getCustomerId());
        
        if (delivery.getAddress() != null) {
            event.setRecipientName(delivery.getAddress().getRecipientName());
            event.setStreet(delivery.getAddress().getStreet());
            event.setCity(delivery.getAddress().getCity());
            event.setState(delivery.getAddress().getState());
            event.setCountry(delivery.getAddress().getCountry());
            event.setZipCode(delivery.getAddress().getZipCode());
            event.setPhoneNumber(delivery.getAddress().getPhoneNumber());
        }
        
        if (delivery.getPickupLocation() != null) {
            event.setPickupLongitude(delivery.getPickupLocation().getX());
            event.setPickupLatitude(delivery.getPickupLocation().getY());
        }
        
        if (delivery.getDeliveryLocation() != null) {
            event.setDeliveryLongitude(delivery.getDeliveryLocation().getX());
            event.setDeliveryLatitude(delivery.getDeliveryLocation().getY());
        }
        
        kafkaTemplate.send(DELIVERY_CREATED_TOPIC, delivery.getTrackingNumber(), event);
        log.info("Published delivery created event for tracking number: {}", delivery.getTrackingNumber());
    }
    
    public void publishDeliveryStatusChangedEvent(Delivery delivery) {
        DeliveryStatusUpdatedEvent event = new DeliveryStatusUpdatedEvent();
        event.setEventId(UUID.randomUUID().toString());
        event.setEventType("DELIVERY_STATUS_UPDATED");
        event.setTimestamp(Instant.now());
        event.setSource("delivery-service");
        event.setVersion("1.0");
        
        event.setDeliveryId(delivery.getId().toString());
        event.setTrackingNumber(delivery.getTrackingNumber());
        event.setOrderId(delivery.getOrderId());
        event.setCustomerId(delivery.getCustomerId());
        event.setStatus(delivery.getStatus().toString());
        event.setEstimatedDeliveryTime(delivery.getEstimatedDeliveryTime());
        event.setActualDeliveryTime(delivery.getActualDeliveryTime());
        
        if (delivery.getCourier() != null) {
            event.setCourierId(delivery.getCourier().getId());
            event.setCourierName(delivery.getCourier().getName());
        }
        
        if (delivery.getCurrentLocation() != null) {
            event.setCurrentLongitude(delivery.getCurrentLocation().getX());
            event.setCurrentLatitude(delivery.getCurrentLocation().getY());
        }
        
        kafkaTemplate.send(DELIVERY_STATUS_UPDATED_TOPIC, delivery.getTrackingNumber(), event);
        log.info("Published delivery status changed event for tracking number: {}, new status: {}",
                delivery.getTrackingNumber(), delivery.getStatus());
    }
    
    public void publishDeliveryLocationUpdatedEvent(Delivery delivery) {
        // This method now delegates to publishDeliveryStatusChangedEvent since location updates
        // are part of status updates in the new event model
        publishDeliveryStatusChangedEvent(delivery);
    }
    
}