package com.ironsoftware.productservice.event;

import com.ironsoftware.common.events.BaseEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ProductEvent extends BaseEvent {
    private String productId;
    private String productName;
    private Object data; // Flexible payload for different event types
    
    public ProductEvent(String eventId, String eventType, String productId, String productName, Instant timestamp, Object data) {
        super(eventId, eventType, timestamp, "product-service", "1.0");
        this.productId = productId;
        this.productName = productName;
        this.data = data;
    }
}