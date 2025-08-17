package com.ironsoftware.deliveryservice.entity;

public enum DeliveryStatus {
    PENDING,
    ASSIGNED,
    PICKED_UP,
    IN_TRANSIT,
    OUT_FOR_DELIVERY,
    DELIVERED,
    FAILED,
    CANCELLED,
    RETURNED
}