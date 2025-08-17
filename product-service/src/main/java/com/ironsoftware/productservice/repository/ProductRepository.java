package com.ironsoftware.productservice.repository;

import com.ironsoftware.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
