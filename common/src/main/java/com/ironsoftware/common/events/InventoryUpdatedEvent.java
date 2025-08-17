package com.ironsoftware.common.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryUpdatedEvent {
    private String productId;
    private Integer quantity;
    private Integer availableStock;
    private String status;
    private String orderId;
}
