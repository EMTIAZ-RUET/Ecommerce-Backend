package com.ironsoftware.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "inventory-service", url = "${services.inventory-service.url:http://localhost:8084}")
public interface InventoryServiceClient {
    
    @GetMapping("/api/inventory/check/{productId}")
    boolean checkAvailability(@PathVariable String productId, @RequestParam int quantity);
    
    @PostMapping("/api/inventory/reserve/{productId}")
    void reserveStock(@PathVariable String productId, @RequestParam int quantity);
    
    @PostMapping("/api/inventory/release/{productId}")
    void releaseReservation(@PathVariable String productId, @RequestParam int quantity);
}
