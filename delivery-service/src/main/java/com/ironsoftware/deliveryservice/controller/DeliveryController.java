package com.ironsoftware.deliveryservice.controller;

import com.ironsoftware.deliveryservice.entity.Delivery;
import com.ironsoftware.deliveryservice.entity.DeliveryStatus;
import com.ironsoftware.deliveryservice.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
@RequiredArgsConstructor
public class DeliveryController {
    private final DeliveryService deliveryService;
    
    @GetMapping("/{trackingNumber}")
    public ResponseEntity<Delivery> getDeliveryByTrackingNumber(@PathVariable String trackingNumber) {
        return ResponseEntity.ok(deliveryService.findByTrackingNumber(trackingNumber));
    }
    
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Delivery>> getDeliveriesByCustomerId(@PathVariable String customerId) {
        return ResponseEntity.ok(deliveryService.findByCustomerId(customerId));
    }
    
    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<Delivery>> getDeliveriesByOrderId(@PathVariable String orderId) {
        return ResponseEntity.ok(deliveryService.findByOrderId(orderId));
    }
    
    @GetMapping("/courier/{courierId}")
    public ResponseEntity<List<Delivery>> getDeliveriesByCourierId(@PathVariable Long courierId) {
        return ResponseEntity.ok(deliveryService.findByCourierId(courierId));
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Delivery>> getDeliveriesByStatus(@PathVariable DeliveryStatus status) {
        return ResponseEntity.ok(deliveryService.findByStatus(status));
    }
    
    @GetMapping("/nearby")
    public ResponseEntity<List<Delivery>> getDeliveriesNearLocation(
            @RequestParam double longitude,
            @RequestParam double latitude,
            @RequestParam(defaultValue = "5000") double radiusInMeters) {
        return ResponseEntity.ok(deliveryService.findDeliveriesNearLocation(longitude, latitude, radiusInMeters));
    }
    
    @PutMapping("/{trackingNumber}/assign/{courierId}")
    public ResponseEntity<Delivery> assignCourier(
            @PathVariable String trackingNumber,
            @PathVariable Long courierId) {
        return ResponseEntity.ok(deliveryService.assignCourier(trackingNumber, courierId));
    }
    
    @PutMapping("/{trackingNumber}/status")
    public ResponseEntity<Delivery> updateDeliveryStatus(
            @PathVariable String trackingNumber,
            @RequestParam DeliveryStatus status) {
        return ResponseEntity.ok(deliveryService.updateDeliveryStatus(trackingNumber, status));
    }
    
    @PutMapping("/{trackingNumber}/location")
    public ResponseEntity<Delivery> updateCurrentLocation(
            @PathVariable String trackingNumber,
            @RequestParam double longitude,
            @RequestParam double latitude) {
        return ResponseEntity.ok(deliveryService.updateCurrentLocation(trackingNumber, longitude, latitude));
    }
}