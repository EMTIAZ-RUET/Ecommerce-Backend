package com.ironsoftware.cartservice.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartEvent {
    private String cartId;
    private String userId;
    private String eventType;
    private String productId;
    private Integer quantity;
    private LocalDateTime timestamp;
}
