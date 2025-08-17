package com.ironsoftware.paymentservice.service;

import com.ironsoftware.paymentservice.dto.PaymentRequest;
import com.ironsoftware.paymentservice.dto.PaymentResponse;
import com.ironsoftware.paymentservice.model.Payment;
import com.ironsoftware.paymentservice.model.PaymentMethod;
import com.ironsoftware.paymentservice.model.PaymentStatus;
import com.ironsoftware.paymentservice.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private PaymentProcessor paymentProcessor;

    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;

    @InjectMocks
    private PaymentService paymentService;

    private Payment testPayment;
    private PaymentRequest paymentRequest;

    @BeforeEach
    void setUp() {
        testPayment = Payment.builder()
                .id(UUID.randomUUID().toString())
                .orderId("order-1")
                .userId("user-1")
                .amount(BigDecimal.valueOf(100.00))
                .paymentMethod(PaymentMethod.CREDIT_CARD)
                .status(PaymentStatus.PENDING)
                .transactionId("txn-123")
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        paymentRequest = PaymentRequest.builder()
                .orderId("order-1")
                .userId("user-1")
                .amount(BigDecimal.valueOf(100.00))
                .paymentMethod(PaymentMethod.CREDIT_CARD)
                .build();
    }

    @Test
    void processPayment_ShouldReturnPaymentResponse_WhenValidRequest() {
        // Given
        when(paymentRepository.save(any(Payment.class))).thenReturn(testPayment);
        doNothing().when(paymentProcessor).processPayment(any(PaymentRequest.class));

        // When
        PaymentResponse result = paymentService.processPayment(paymentRequest);

        // Then
        assertNotNull(result);
        assertEquals(testPayment.getOrderId(), result.getOrderId());
        assertEquals(testPayment.getAmount(), result.getAmount());
        assertEquals(PaymentStatus.PENDING, result.getStatus());
        verify(paymentRepository).save(any(Payment.class));
        verify(paymentProcessor).processPayment(any(PaymentRequest.class));
    }

    @Test
    void getPaymentById_ShouldReturnPaymentResponse_WhenPaymentExists() {
        // Given
        String paymentId = testPayment.getId();
        when(paymentRepository.findById(paymentId)).thenReturn(Optional.of(testPayment));

        // When
        PaymentResponse result = paymentService.getPaymentById(paymentId);

        // Then
        assertNotNull(result);
        assertEquals(testPayment.getId(), result.getId());
        assertEquals(testPayment.getOrderId(), result.getOrderId());
        assertEquals(testPayment.getStatus(), result.getStatus());
    }

    @Test
    void getPaymentById_ShouldThrowException_WhenPaymentNotFound() {
        // Given
        String paymentId = "nonexistent";
        when(paymentRepository.findById(paymentId)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(RuntimeException.class, () -> paymentService.getPaymentById(paymentId));
    }

    @Test
    void getPaymentsByOrderId_ShouldReturnPaymentList_WhenPaymentsExist() {
        // Given
        String orderId = "order-1";
        List<Payment> payments = Arrays.asList(testPayment);
        when(paymentRepository.findByOrderId(orderId)).thenReturn(payments);

        // When
        List<PaymentResponse> result = paymentService.getPaymentsByOrderId(orderId);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testPayment.getId(), result.get(0).getId());
    }

    @Test
    void updatePaymentStatus_ShouldUpdateStatus_WhenPaymentExists() {
        // Given
        String paymentId = testPayment.getId();
        PaymentStatus newStatus = PaymentStatus.COMPLETED;
        when(paymentRepository.findById(paymentId)).thenReturn(Optional.of(testPayment));
        when(paymentRepository.save(any(Payment.class))).thenReturn(testPayment);

        // When
        paymentService.updatePaymentStatus(paymentId, newStatus);

        // Then
        verify(paymentRepository).save(argThat(payment -> payment.getStatus() == newStatus));
        verify(kafkaTemplate).send(anyString(), any());
    }

    @Test
    void refundPayment_ShouldSetStatusToRefunded_WhenPaymentExists() {
        // Given
        String paymentId = testPayment.getId();
        testPayment.setStatus(PaymentStatus.COMPLETED);
        when(paymentRepository.findById(paymentId)).thenReturn(Optional.of(testPayment));

        // When
        paymentService.refundPayment(paymentId);

        // Then
        verify(paymentRepository).save(argThat(payment -> payment.getStatus() == PaymentStatus.REFUNDED));
    }
}
