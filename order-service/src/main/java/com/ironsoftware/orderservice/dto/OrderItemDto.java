package com.ironsoftware.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
    private String id;
    private String productId;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal subtotal;
}
