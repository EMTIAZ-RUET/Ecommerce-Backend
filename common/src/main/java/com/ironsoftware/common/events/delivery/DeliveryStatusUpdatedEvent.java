package com.ironsoftware.common.events.delivery;

import com.ironsoftware.common.events.BaseEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;

@Data
@EqualsAndHashCode(callSuper = true)
public class DeliveryStatusUpdatedEvent extends BaseEvent {
    private String deliveryId;
    private String trackingNumber;
    private String orderId;
    private String customerId;
    private String status;
    private Double currentLongitude;
    private Double currentLatitude;
    private Instant estimatedDeliveryTime;
    private Instant actualDeliveryTime;
    private String courierName;
    private Long courierId;
}