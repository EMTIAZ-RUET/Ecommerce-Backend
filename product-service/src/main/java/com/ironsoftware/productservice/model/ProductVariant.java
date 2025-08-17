package com.ironsoftware.productservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;

@Document(collection = "product_variants")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductVariant {
    @Id
    private String id;
    
    @Indexed
    private String productId;
    
    @Indexed(unique = true)
    private String sku;
    
    private String name;
    
    // Using Object instead of String for more flexible attribute values
    private Map<String, Object> attributes;
    
    private BigDecimal priceModifier;
    private Integer stockQuantity;
    
    @Indexed(unique = true, sparse = true)
    private String barcode;
    
    private boolean active = true;
    
    private Instant createdAt;
    private Instant updatedAt;
    
    // Business methods
    public void updateStock(Integer newStock) {
        this.stockQuantity = newStock;
        this.updatedAt = Instant.now();
    }
    
    public BigDecimal calculateFinalPrice(BigDecimal basePrice) {
        if (priceModifier == null) {
            return basePrice;
        }
        return basePrice.add(priceModifier);
    }
    
    public boolean isInStock() {
        return stockQuantity != null && stockQuantity > 0;
    }
    
    public void activate() {
        this.active = true;
        this.updatedAt = Instant.now();
    }
    
    public void deactivate() {
        this.active = false;
        this.updatedAt = Instant.now();
    }
}
