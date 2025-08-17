package com.ironsoftware.orderservice.entity;

public enum OrderStatus {
    CREATED,
    PENDING,
    CONFIRMED,
    PENDING_PAYMENT,
    PAID,
    SHIPPING,
    DELIVERED,
    COMPLETED,
    CANCELLED
}