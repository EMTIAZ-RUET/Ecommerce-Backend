package com.ironsoftware.productservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Map;

@Document(collection = "products")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    private String id;
    
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private String category;
    private List<String> tags;
    
    // Product variants - using DBRef for MongoDB relationship
    @DBRef
    private List<ProductVariant> variants;
    
    private String sellerId;
    
    // Dynamic attributes for flexible schema
    private Map<String, Object> attributes;
    
    @CreatedDate
    private Instant createdAt;
    
    @LastModifiedDate
    private Instant updatedAt;
    
    @Builder.Default
    private boolean active = true;
    
    // Business methods
    public void updateStock(Integer newStock) {
        this.stock = newStock;
        this.updatedAt = Instant.now();
    }
    
    public void activate() {
        this.active = true;
        this.updatedAt = Instant.now();
    }
    
    public void deactivate() {
        this.active = false;
        this.updatedAt = Instant.now();
    }
    
    public BigDecimal calculateTotalStock() {
        if (variants == null || variants.isEmpty()) {
            return BigDecimal.valueOf(stock != null ? stock : 0);
        }
        return variants.stream()
                .map(variant -> BigDecimal.valueOf(variant.getStockQuantity() != null ? variant.getStockQuantity() : 0))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
