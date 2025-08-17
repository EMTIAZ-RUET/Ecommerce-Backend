package com.ironsoftware.productservice.event;

import com.ironsoftware.productservice.model.Product;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductDeletedEvent extends ProductEvent {

    @Builder
    public ProductDeletedEvent(Product product) {
        super(
                UUID.randomUUID().toString(),
                "PRODUCT_DELETED",
                product.getId(),
                product.getName(),
                Instant.now(),
                product
        );
    }
}