package com.ironsoftware.orderservice.client;

import com.ironsoftware.common.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "product-service", url = "${services.product-service.url:http://localhost:8083}")
public interface ProductServiceClient {
    
    @GetMapping("/api/products/{id}")
    ProductDto getProduct(@PathVariable String id);
    
    @GetMapping("/api/products/{id}/exists")
    boolean productExists(@PathVariable String id);
}
