package com.ironsoftware.productservice.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CreateProductRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private String category;
    private List<String> tags;
    private Integer stockQuantity;
    private List<CreateProductVariantRequest> variants;
}