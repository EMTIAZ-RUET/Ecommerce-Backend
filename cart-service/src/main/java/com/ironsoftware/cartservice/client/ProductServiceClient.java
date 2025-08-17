package com.ironsoftware.cartservice.client;

import com.ironsoftware.common.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service", url = "${services.product-service.url:http://localhost:8082}")
public interface ProductServiceClient {
    
    @GetMapping("/api/products/{id}")
    ProductDto getProduct(@PathVariable String id);
    
    @GetMapping("/api/products/exists/{id}")
    boolean productExists(@PathVariable String id);
}
