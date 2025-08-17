package com.ironsoftware.paymentservice.dto;

import com.ironsoftware.paymentservice.model.PaymentMethod;
import com.ironsoftware.paymentservice.model.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {
    private String id;
    private String orderId;
    private String userId;
    private BigDecimal amount;
    private String transactionId;
    private PaymentStatus status;
    private PaymentMethod paymentMethod;
    private Instant createdAt;
    private String errorMessage;
}
