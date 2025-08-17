package com.ironsoftware.inventoryservice.dto;

import com.ironsoftware.inventoryservice.model.InventoryStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryResponse {
    private String productId;
    private Integer availableQuantity;
    private InventoryStatus status;
    private boolean isAvailable;
}
