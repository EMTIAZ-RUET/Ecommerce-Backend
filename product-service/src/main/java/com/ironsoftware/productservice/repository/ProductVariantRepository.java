package com.ironsoftware.productservice.repository;

import com.ironsoftware.productservice.model.ProductVariant;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductVariantRepository extends MongoRepository<ProductVariant, String> {
    List<ProductVariant> findByProductId(String productId);
}