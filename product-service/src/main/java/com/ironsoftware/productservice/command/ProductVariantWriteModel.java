package com.ironsoftware.productservice.command;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class ProductVariantWriteModel {
    private String id;
    private String name;
    private String sku;
    private BigDecimal price;
    private Integer stockQuantity;
    private Map<String, String> attributes;
}