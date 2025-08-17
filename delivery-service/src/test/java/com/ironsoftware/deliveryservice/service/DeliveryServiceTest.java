package com.ironsoftware.deliveryservice.service;

import com.ironsoftware.deliveryservice.model.Delivery;
import com.ironsoftware.deliveryservice.model.DeliveryStatus;
import com.ironsoftware.deliveryservice.repository.DeliveryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeliveryServiceTest {

    @Mock
    private DeliveryRepository deliveryRepository;

    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;

    @InjectMocks
    private DeliveryService deliveryService;

    private Delivery testDelivery;

    @BeforeEach
    void setUp() {
        testDelivery = Delivery.builder()
                .id("delivery-1")
                .orderId("order-1")
                .userId("user-1")
                .recipientName("John Doe")
                .recipientPhone("+1234567890")
                .shippingAddress("123 Main St, City, State 12345")
                .status(DeliveryStatus.PENDING)
                .trackingNumber("TRACK123456")
                .estimatedDeliveryDate(Instant.now().plusSeconds(86400 * 3)) // 3 days
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
    }

    @Test
    void findById_ShouldReturnDelivery_WhenDeliveryExists() {
        // Given
        when(deliveryRepository.findById(anyString())).thenReturn(Optional.of(testDelivery));

        // When
        Optional<Delivery> result = deliveryRepository.findById("delivery-1");

        // Then
        assertTrue(result.isPresent());
        assertEquals(testDelivery.getId(), result.get().getId());
        assertEquals(testDelivery.getOrderId(), result.get().getOrderId());
    }

    @Test
    void findByOrderId_ShouldReturnDelivery_WhenOrderExists() {
        // Given
        when(deliveryRepository.findByOrderId(anyString())).thenReturn(Optional.of(testDelivery));

        // When
        Optional<Delivery> result = deliveryRepository.findByOrderId("order-1");

        // Then
        assertTrue(result.isPresent());
        assertEquals(testDelivery.getOrderId(), result.get().getOrderId());
    }

    @Test
    void findByUserId_ShouldReturnDeliveries_WhenUserHasDeliveries() {
        // Given
        List<Delivery> deliveries = Arrays.asList(testDelivery);
        when(deliveryRepository.findByUserId(anyString())).thenReturn(deliveries);

        // When
        List<Delivery> result = deliveryRepository.findByUserId("user-1");

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testDelivery.getUserId(), result.get(0).getUserId());
    }

    @Test
    void findByTrackingNumber_ShouldReturnDelivery_WhenTrackingNumberExists() {
        // Given
        when(deliveryRepository.findByTrackingNumber(anyString())).thenReturn(Optional.of(testDelivery));

        // When
        Optional<Delivery> result = deliveryRepository.findByTrackingNumber("TRACK123456");

        // Then
        assertTrue(result.isPresent());
        assertEquals(testDelivery.getTrackingNumber(), result.get().getTrackingNumber());
    }

    @Test
    void save_ShouldReturnSavedDelivery_WhenValidDelivery() {
        // Given
        when(deliveryRepository.save(any(Delivery.class))).thenReturn(testDelivery);

        // When
        Delivery result = deliveryRepository.save(testDelivery);

        // Then
        assertNotNull(result);
        assertEquals(testDelivery.getId(), result.getId());
        assertEquals(testDelivery.getStatus(), result.getStatus());
    }

    @Test
    void updateDeliveryStatus_ShouldUpdateStatus_WhenDeliveryExists() {
        // Given
        when(deliveryRepository.findById(anyString())).thenReturn(Optional.of(testDelivery));
        when(deliveryRepository.save(any(Delivery.class))).thenReturn(testDelivery);

        // When
        testDelivery.setStatus(DeliveryStatus.IN_TRANSIT);
        Delivery result = deliveryRepository.save(testDelivery);

        // Then
        assertEquals(DeliveryStatus.IN_TRANSIT, result.getStatus());
        verify(deliveryRepository).save(any(Delivery.class));
    }
}
