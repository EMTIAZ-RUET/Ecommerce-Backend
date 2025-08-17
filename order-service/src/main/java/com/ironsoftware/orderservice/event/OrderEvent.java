package com.ironsoftware.orderservice.event;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "order_events")
@Data
public class OrderEvent {
    @Id
    private String id;
    private String orderId;
    private String eventType;
    private Object payload;
    private Integer version;
    private Instant timestamp;
    private String userId;
}
