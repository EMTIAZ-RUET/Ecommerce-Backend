package com.ironsoftware.productservice.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
public class ProductDTO {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private String category;
    private List<String> tags;
    private Integer stockQuantity;
    private List<ProductVariantDTO> variants;
    private Double averageRating;
    private Integer reviewCount;
}
