package com.ironsoftware.productservice.event;

import com.ironsoftware.productservice.model.Product;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductStockUpdatedEvent extends ProductEvent {

    private Integer previousStock;
    private Integer currentStock;

    @Builder
    public ProductStockUpdatedEvent(Product product, Integer previousStock, Integer currentStock) {
        super(
                UUID.randomUUID().toString(),
                "PRODUCT_STOCK_UPDATED",
                product.getId(),
                product.getName(),
                Instant.now(),
                product
        );
        this.previousStock = previousStock;
        this.currentStock = currentStock;
    }
}