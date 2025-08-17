package com.ironsoftware.common.events.payment;

import com.ironsoftware.common.events.BaseEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class PaymentProcessedEvent extends BaseEvent {
    private String paymentId;
    private String orderId;
    private String userId;
    private BigDecimal amount;
    private String status;
    private String paymentMethod;
    private String transactionId;
}
