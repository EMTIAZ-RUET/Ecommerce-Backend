package com.ironsoftware.deliveryservice.controller;

import com.ironsoftware.deliveryservice.entity.Courier;
import com.ironsoftware.deliveryservice.service.CourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/couriers")
@RequiredArgsConstructor
public class CourierController {
    private final CourierService courierService;
    
    @GetMapping
    public ResponseEntity<List<Courier>> getAllCouriers() {
        return ResponseEntity.ok(courierService.findAllCouriers());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Courier> getCourierById(@PathVariable Long id) {
        return ResponseEntity.ok(courierService.findById(id));
    }
    
    @GetMapping("/active")
    public ResponseEntity<List<Courier>> getActiveCouriers() {
        return ResponseEntity.ok(courierService.findActiveCouriers());
    }
    
    @GetMapping("/available")
    public ResponseEntity<List<Courier>> getAvailableCouriers() {
        return ResponseEntity.ok(courierService.findAvailableCouriers());
    }
    
    @GetMapping("/nearby")
    public ResponseEntity<List<Courier>> getCouriersNearLocation(
            @RequestParam double longitude,
            @RequestParam double latitude,
            @RequestParam(defaultValue = "5000") double radiusInMeters) {
        return ResponseEntity.ok(courierService.findCouriersNearLocation(longitude, latitude, radiusInMeters));
    }
    
    @PostMapping
    public ResponseEntity<Courier> createCourier(@RequestBody Courier courier) {
        return new ResponseEntity<>(courierService.createCourier(courier), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Courier> updateCourier(
            @PathVariable Long id,
            @RequestBody Courier courier) {
        return ResponseEntity.ok(courierService.updateCourier(id, courier));
    }
    
    @PutMapping("/{id}/location")
    public ResponseEntity<Courier> updateCourierLocation(
            @PathVariable Long id,
            @RequestParam double longitude,
            @RequestParam double latitude) {
        return ResponseEntity.ok(courierService.updateCourierLocation(id, longitude, latitude));
    }
    
    @PutMapping("/{id}/toggle-active")
    public ResponseEntity<Courier> toggleCourierActiveStatus(@PathVariable Long id) {
        return ResponseEntity.ok(courierService.toggleCourierActiveStatus(id));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourier(@PathVariable Long id) {
        courierService.deleteCourier(id);
        return ResponseEntity.noContent().build();
    }
}