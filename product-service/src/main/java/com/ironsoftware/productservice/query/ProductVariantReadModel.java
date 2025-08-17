package com.ironsoftware.productservice.query;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class ProductVariantReadModel {
    private String id;
    private String name;
    private String sku;
    private BigDecimal price;
    private Integer stockQuantity;
    private Map<String, String> attributes;
}