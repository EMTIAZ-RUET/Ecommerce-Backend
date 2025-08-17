package com.ironsoftware.orderservice.entity;

public enum OrderStatus {
    CREATED,
    PENDING_PAYMENT,
    PAID,
    SHIPPING,
    DELIVERED,
    CANCELLED
}