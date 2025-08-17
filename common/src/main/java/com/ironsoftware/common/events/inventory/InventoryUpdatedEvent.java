package com.ironsoftware.common.events.inventory;

import com.ironsoftware.common.events.BaseEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class InventoryUpdatedEvent extends BaseEvent {
    private String productId;
    private String variantId;
    private Integer quantity;
    private String operation; // DECREASE, INCREASE
    private String orderId;
}
