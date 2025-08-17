package com.ironsoftware.deliveryservice.event;

import com.ironsoftware.deliveryservice.entity.DeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryEvent {
    private String eventId;
    private String eventType;
    private Instant timestamp;
    private String deliveryId;
    private String trackingNumber;
    private String orderId;
    private String customerId;
    private DeliveryStatus status;
    private Double currentLongitude;
    private Double currentLatitude;
    private Instant estimatedDeliveryTime;
    private Instant actualDeliveryTime;
    private String courierName;
    private Long courierId;
}