package com.ironsoftware.deliveryservice.service;

import com.ironsoftware.deliveryservice.entity.Courier;
import com.ironsoftware.deliveryservice.exception.CourierNotFoundException;
import com.ironsoftware.deliveryservice.repository.CourierRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourierService {
    private final CourierRepository courierRepository;
    private final GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
    
    public List<Courier> findAllCouriers() {
        return courierRepository.findAll();
    }
    
    public Courier findById(Long id) {
        return courierRepository.findById(id)
                .orElseThrow(() -> new CourierNotFoundException("Courier not found with id: " + id));
    }
    
    public List<Courier> findActiveCouriers() {
        return courierRepository.findByIsActiveTrue();
    }
    
    public List<Courier> findAvailableCouriers() {
        return courierRepository.findAvailableCouriers();
    }
    
    public List<Courier> findCouriersNearLocation(double longitude, double latitude, double radiusInMeters) {
        return courierRepository.findCouriersNearLocation(longitude, latitude, radiusInMeters);
    }
    
    @Transactional
    public Courier createCourier(Courier courier) {
        return courierRepository.save(courier);
    }
    
    @Transactional
    public Courier updateCourier(Long id, Courier courierDetails) {
        Courier courier = findById(id);
        
        courier.setName(courierDetails.getName());
        courier.setEmail(courierDetails.getEmail());
        courier.setPhoneNumber(courierDetails.getPhoneNumber());
        courier.setVehicleType(courierDetails.getVehicleType());
        courier.setVehicleId(courierDetails.getVehicleId());
        courier.setActive(courierDetails.isActive());
        courier.setMaxCapacity(courierDetails.getMaxCapacity());
        
        return courierRepository.save(courier);
    }
    
    @Transactional
    public Courier updateCourierLocation(Long id, double longitude, double latitude) {
        Courier courier = findById(id);
        Point location = createPoint(longitude, latitude);
        courier.setCurrentLocation(location);
        return courierRepository.save(courier);
    }
    
    @Transactional
    public void deleteCourier(Long id) {
        Courier courier = findById(id);
        courierRepository.delete(courier);
    }
    
    @Transactional
    public Courier toggleCourierActiveStatus(Long id) {
        Courier courier = findById(id);
        courier.setActive(!courier.isActive());
        return courierRepository.save(courier);
    }
    
    private Point createPoint(double longitude, double latitude) {
        return geometryFactory.createPoint(new Coordinate(longitude, latitude));
    }
}