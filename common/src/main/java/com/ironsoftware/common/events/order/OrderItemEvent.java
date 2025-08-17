package com.ironsoftware.common.events.order;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemEvent {
    private String productId;
    private String productName;
    private Integer quantity;
    private BigDecimal price;
    private String variantId;
}