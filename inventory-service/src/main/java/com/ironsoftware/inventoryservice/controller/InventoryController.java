package com.ironsoftware.inventoryservice.controller;

import com.ironsoftware.inventoryservice.dto.InventoryResponse;
import com.ironsoftware.inventoryservice.model.InventoryItem;
import com.ironsoftware.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public InventoryResponse checkInventory(@PathVariable String productId) {
        return inventoryService.checkInventory(productId);
    }

    @PostMapping("/check-batch")
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> checkInventoryBatch(@RequestBody List<String> productIds) {
        return inventoryService.checkInventoryBatch(productIds);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public InventoryResponse updateInventory(@RequestBody InventoryItem item) {
        return inventoryService.updateInventory(item);
    }
}
