package com.ironsoftware.deliveryservice.service;

import com.ironsoftware.deliveryservice.entity.Courier;
import com.ironsoftware.deliveryservice.entity.Delivery;
import com.ironsoftware.deliveryservice.entity.DeliveryAddress;
import com.ironsoftware.deliveryservice.entity.DeliveryStatus;
import com.ironsoftware.deliveryservice.event.DeliveryEvent;
import com.ironsoftware.deliveryservice.event.DeliveryEventPublisher;
import com.ironsoftware.deliveryservice.exception.DeliveryNotFoundException;
import com.ironsoftware.deliveryservice.repository.CourierRepository;
import com.ironsoftware.deliveryservice.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;
    private final CourierRepository courierRepository;
    private final DeliveryEventPublisher eventPublisher;
    private final GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
    
    @Transactional
    public Delivery createDelivery(String orderId, String customerId, DeliveryAddress address, 
                                  double pickupLongitude, double pickupLatitude,
                                  double deliveryLongitude, double deliveryLatitude) {
        Point pickupLocation = createPoint(pickupLongitude, pickupLatitude);
        Point deliveryLocation = createPoint(deliveryLongitude, deliveryLatitude);
        
        Delivery delivery = Delivery.builder()
                .orderId(orderId)
                .customerId(customerId)
                .trackingNumber(generateTrackingNumber())
                .status(DeliveryStatus.PENDING)
                .address(address)
                .pickupLocation(pickupLocation)
                .deliveryLocation(deliveryLocation)
                .estimatedDeliveryTime(Instant.now().plusSeconds(86400)) // 24 hours from now
                .build();
        
        delivery = deliveryRepository.save(delivery);
        
        // Publish delivery created event
        eventPublisher.publishDeliveryCreatedEvent(delivery);
        
        return delivery;
    }
    
    @Transactional
    public Delivery assignCourier(String trackingNumber, Long courierId) {
        Delivery delivery = findByTrackingNumber(trackingNumber);
        Courier courier = courierRepository.findById(courierId)
                .orElseThrow(() -> new RuntimeException("Courier not found"));
        
        delivery.setCourier(courier);
        delivery.setStatus(DeliveryStatus.ASSIGNED);
        delivery = deliveryRepository.save(delivery);
        
        // Update courier's current load
        courier.setCurrentLoad(courier.getCurrentLoad() + 1);
        courierRepository.save(courier);
        
        // Publish delivery assigned event
        eventPublisher.publishDeliveryStatusChangedEvent(delivery);
        
        return delivery;
    }
    
    @Transactional
    public Delivery updateDeliveryStatus(String trackingNumber, DeliveryStatus status) {
        Delivery delivery = findByTrackingNumber(trackingNumber);
        delivery.setStatus(status);
        
        if (status == DeliveryStatus.DELIVERED) {
            delivery.setActualDeliveryTime(Instant.now());
            
            // Update courier's current load
            Courier courier = delivery.getCourier();
            if (courier != null) {
                courier.setCurrentLoad(courier.getCurrentLoad() - 1);
                courierRepository.save(courier);
            }
        }
        
        delivery = deliveryRepository.save(delivery);
        
        // Publish delivery status changed event
        eventPublisher.publishDeliveryStatusChangedEvent(delivery);
        
        return delivery;
    }
    
    @Transactional
    public Delivery updateCurrentLocation(String trackingNumber, double longitude, double latitude) {
        Delivery delivery = findByTrackingNumber(trackingNumber);
        Point location = createPoint(longitude, latitude);
        delivery.setCurrentLocation(location);
        delivery = deliveryRepository.save(delivery);
        
        // Publish delivery location updated event
        eventPublisher.publishDeliveryLocationUpdatedEvent(delivery);
        
        return delivery;
    }
    
    public Delivery findByTrackingNumber(String trackingNumber) {
        return deliveryRepository.findByTrackingNumber(trackingNumber)
                .orElseThrow(() -> new DeliveryNotFoundException("Delivery not found with tracking number: " + trackingNumber));
    }
    
    public List<Delivery> findByCustomerId(String customerId) {
        return deliveryRepository.findByCustomerId(customerId);
    }
    
    public List<Delivery> findByOrderId(String orderId) {
        return deliveryRepository.findByOrderId(orderId);
    }
    
    public List<Delivery> findByCourierId(Long courierId) {
        return deliveryRepository.findByCourierId(courierId);
    }
    
    public List<Delivery> findByStatus(DeliveryStatus status) {
        return deliveryRepository.findByStatus(status);
    }
    
    public List<Delivery> findDeliveriesNearLocation(double longitude, double latitude, double radiusInMeters) {
        return deliveryRepository.findDeliveriesWithinRadius(longitude, latitude, radiusInMeters);
    }
    
    private Point createPoint(double longitude, double latitude) {
        return geometryFactory.createPoint(new Coordinate(longitude, latitude));
    }
    
    private String generateTrackingNumber() {
        return "TRK-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}