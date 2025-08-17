package com.ironsoftware.deliveryservice.repository;

import com.ironsoftware.deliveryservice.entity.Courier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourierRepository extends JpaRepository<Courier, Long> {
    Optional<Courier> findByEmail(String email);
    
    List<Courier> findByIsActiveTrue();
    
    @Query("SELECT c FROM Courier c WHERE c.isActive = true AND c.currentLoad < c.maxCapacity")
    List<Courier> findAvailableCouriers();
    
    @Query(value = "SELECT * FROM couriers c WHERE c.is_active = true AND ST_DWithin(c.current_location, ST_SetSRID(ST_MakePoint(:longitude, :latitude), 4326), :radiusInMeters)", nativeQuery = true)
    List<Courier> findCouriersNearLocation(double longitude, double latitude, double radiusInMeters);
}