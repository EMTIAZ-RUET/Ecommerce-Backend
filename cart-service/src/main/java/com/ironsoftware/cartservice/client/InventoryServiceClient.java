package com.ironsoftware.cartservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "inventory-service", url = "${services.inventory-service.url:http://localhost:8084}")
public interface InventoryServiceClient {
    
    @GetMapping("/api/inventory/check/{productId}")
    boolean checkAvailability(@PathVariable String productId, @RequestParam int quantity);
}
