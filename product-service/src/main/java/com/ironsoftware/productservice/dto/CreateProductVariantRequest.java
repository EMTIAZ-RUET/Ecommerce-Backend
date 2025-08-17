package com.ironsoftware.productservice.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class CreateProductVariantRequest {
    private String name;
    private String sku;
    private BigDecimal price;
    private Integer stockQuantity;
    private Map<String, String> attributes;
}