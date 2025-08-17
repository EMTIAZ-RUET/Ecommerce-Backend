package com.ironsoftware.deliveryservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.Instant;

@Entity
@Table(name = "deliveries")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "order_id", nullable = false)
    private String orderId;
    
    @Column(name = "tracking_number", unique = true)
    private String trackingNumber;
    
    @Column(name = "customer_id", nullable = false)
    private String customerId;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "courier_id")
    private Courier courier;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryStatus status;
    
    @Column(name = "estimated_delivery_time")
    private Instant estimatedDeliveryTime;
    
    @Column(name = "actual_delivery_time")
    private Instant actualDeliveryTime;
    
    @Column(name = "pickup_location")
    private Point pickupLocation;
    
    @Column(name = "delivery_location")
    private Point deliveryLocation;
    
    @Column(name = "current_location")
    private Point currentLocation;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private DeliveryAddress address;
    
    @Column(name = "notes")
    private String notes;
    
    @Column(name = "signature_required")
    private boolean signatureRequired;
    
    @Column(name = "signature_image")
    private String signatureImage;
    
    @Version
    private Long version;
    
    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;
}