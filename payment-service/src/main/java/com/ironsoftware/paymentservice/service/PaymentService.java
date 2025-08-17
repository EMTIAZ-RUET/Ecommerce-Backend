package com.ironsoftware.paymentservice.service;

import com.ironsoftware.paymentservice.dto.PaymentRequest;
import com.ironsoftware.paymentservice.dto.PaymentResponse;
import com.ironsoftware.paymentservice.event.PaymentEvent;
import com.ironsoftware.paymentservice.model.Payment;
import com.ironsoftware.paymentservice.model.PaymentStatus;
import com.ironsoftware.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentProcessor paymentProcessor;
    private final KafkaTemplate<String, PaymentEvent> kafkaTemplate;

    @Transactional
    public PaymentResponse processPayment(PaymentRequest request) {
        Payment payment = Payment.builder()
                .orderId(request.getOrderId())
                .userId(request.getUserId())
                .amount(request.getAmount())
                .paymentMethod(request.getPaymentMethod())
                .status(PaymentStatus.PROCESSING)
                .transactionId(UUID.randomUUID().toString())
                .build();

        try {
            paymentProcessor.processPayment(request);
            payment.setStatus(PaymentStatus.COMPLETED);
        } catch (Exception e) {
            payment.setStatus(PaymentStatus.FAILED);
            payment.setErrorMessage(e.getMessage());
            log.error("Payment processing failed", e);
        }

        payment = paymentRepository.save(payment);

        // Publish payment event
        PaymentEvent paymentEvent = PaymentEvent.builder()
                .paymentId(payment.getId().toString())
                .orderId(payment.getOrderId())
                .userId(payment.getUserId())
                .amount(payment.getAmount())
                .status(payment.getStatus())
                .paymentMethod(payment.getPaymentMethod().toString())
                .transactionId(payment.getTransactionId())
                .build();
        
        // Set base event fields
        paymentEvent.setEventId(java.util.UUID.randomUUID().toString());
        paymentEvent.setEventType("PAYMENT_PROCESSED");
        paymentEvent.setTimestamp(java.time.Instant.now());
        paymentEvent.setSource("payment-service");
        paymentEvent.setVersion("1.0");
        
        kafkaTemplate.send("payment-events", paymentEvent);

        return mapToResponse(payment);
    }

    public List<PaymentResponse> getPaymentsByOrder(String orderId) {
        return paymentRepository.findByOrderId(orderId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<PaymentResponse> getPaymentsByUser(String userId) {
        return paymentRepository.findByUserId(userId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private PaymentResponse mapToResponse(Payment payment) {
        return PaymentResponse.builder()
                .id(payment.getId())
                .orderId(payment.getOrderId())
                .userId(payment.getUserId())
                .amount(payment.getAmount())
                .transactionId(payment.getTransactionId())
                .status(payment.getStatus())
                .paymentMethod(payment.getPaymentMethod())
                .createdAt(payment.getCreatedAt())
                .errorMessage(payment.getErrorMessage())
                .build();
    }
}
