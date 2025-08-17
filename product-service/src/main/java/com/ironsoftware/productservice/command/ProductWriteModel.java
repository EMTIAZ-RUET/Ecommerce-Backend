package com.ironsoftware.productservice.command;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Document(collection = "products_write")
@Data
public class ProductWriteModel {
    @Id
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private String category;
    private List<String> tags;
    private Integer stockQuantity;
    private List<ProductVariantWriteModel> variants;
    private String sellerId;
    private Instant createdAt;
    private Instant updatedAt;
    private boolean active;
}
