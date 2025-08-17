package com.ironsoftware.deliveryservice.repository;

import com.ironsoftware.deliveryservice.entity.Delivery;
import com.ironsoftware.deliveryservice.entity.DeliveryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    Optional<Delivery> findByTrackingNumber(String trackingNumber);
    
    List<Delivery> findByCustomerId(String customerId);
    
    List<Delivery> findByOrderId(String orderId);
    
    List<Delivery> findByCourierId(Long courierId);
    
    List<Delivery> findByStatus(DeliveryStatus status);
    
    List<Delivery> findByStatusAndEstimatedDeliveryTimeBefore(DeliveryStatus status, Instant time);
    
    @Query("SELECT d FROM Delivery d WHERE d.status = :status AND d.courier.id = :courierId")
    List<Delivery> findByStatusAndCourierId(DeliveryStatus status, Long courierId);
    
    @Query(value = "SELECT * FROM deliveries d WHERE ST_DWithin(d.delivery_location, ST_SetSRID(ST_MakePoint(:longitude, :latitude), 4326), :radiusInMeters)", nativeQuery = true)
    List<Delivery> findDeliveriesWithinRadius(double longitude, double latitude, double radiusInMeters);
}