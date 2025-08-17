package com.ironsoftware.common.events.order;

import com.ironsoftware.common.events.BaseEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderCreatedEvent extends BaseEvent {
    private String orderId;
    private String userId;
    private List<OrderItemEvent> items;
    private BigDecimal totalAmount;
    private String shippingAddress;
    private String paymentMethodId;
}
