package com.ironsoftware.common.events.delivery;

import com.ironsoftware.common.events.BaseEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DeliveryCreatedEvent extends BaseEvent {
    private String deliveryId;
    private String trackingNumber;
    private String orderId;
    private String customerId;
    private String recipientName;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private String phoneNumber;
    private Double pickupLongitude;
    private Double pickupLatitude;
    private Double deliveryLongitude;
    private Double deliveryLatitude;
}