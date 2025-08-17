package com.ironsoftware.inventoryservice.service;

import com.ironsoftware.common.events.order.OrderCreatedEvent;
import com.ironsoftware.common.events.order.OrderItemEvent;
import com.ironsoftware.inventoryservice.model.InventoryItem;
import com.ironsoftware.inventoryservice.model.InventoryStatus;
import com.ironsoftware.inventoryservice.repository.InventoryItemRepository;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InventoryServiceTest {

    @Mock
    private InventoryItemRepository inventoryRepository;

    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;

    @InjectMocks
    private InventoryService inventoryService;

    private InventoryItem testInventoryItem;
    private OrderCreatedEvent orderCreatedEvent;

    @BeforeEach
    void setUp() {
        testInventoryItem = InventoryItem.builder()
                .productId("product-1")
                .quantity(100)
                .reservedQuantity(0)
                .availableQuantity(100)
                .reorderLevel(10)
                .maxStockLevel(500)
                .status(InventoryStatus.IN_STOCK)
                .version(1L)
                .lastUpdated(Instant.now())
                .build();

        OrderItemEvent orderItem = new OrderItemEvent();
        orderItem.setProductId("product-1");
        orderItem.setQuantity(5);
        orderItem.setPrice(BigDecimal.valueOf(50.00));

        orderCreatedEvent = new OrderCreatedEvent();
        orderCreatedEvent.setOrderId("order-1");
        orderCreatedEvent.setUserId("user-1");
        orderCreatedEvent.setItems(Arrays.asList(orderItem));
        orderCreatedEvent.setTotalAmount(BigDecimal.valueOf(250.00));
    }

    @Test
    void checkAvailability_ShouldReturnTrue_WhenSufficientStock() {
        // Given
        String productId = "product-1";
        Integer requestedQuantity = 5;
        when(inventoryRepository.findByProductId(productId)).thenReturn(Optional.of(testInventoryItem));

        // When
        boolean result = inventoryService.checkAvailability(productId, requestedQuantity);

        // Then
        assertTrue(result);
    }

    @Test
    void checkAvailability_ShouldReturnFalse_WhenInsufficientStock() {
        // Given
        String productId = "product-1";
        Integer requestedQuantity = 150;
        when(inventoryRepository.findByProductId(productId)).thenReturn(Optional.of(testInventoryItem));

        // When
        boolean result = inventoryService.checkAvailability(productId, requestedQuantity);

        // Then
        assertFalse(result);
    }

    @Test
    void reserveInventory_ShouldReserveQuantity_WhenSufficientStock() {
        // Given
        String productId = "product-1";
        Integer quantity = 5;
        when(inventoryRepository.findByProductId(productId)).thenReturn(Optional.of(testInventoryItem));
        when(inventoryRepository.save(any(InventoryItem.class))).thenReturn(testInventoryItem);

        // When
        boolean result = inventoryService.reserveInventory(productId, quantity);

        // Then
        assertTrue(result);
        verify(inventoryRepository).save(argThat(item -> 
            item.getReservedQuantity() == 5 && item.getAvailableQuantity() == 95));
    }

    @Test
    void reserveInventory_ShouldReturnFalse_WhenInsufficientStock() {
        // Given
        String productId = "product-1";
        Integer quantity = 150;
        when(inventoryRepository.findByProductId(productId)).thenReturn(Optional.of(testInventoryItem));

        // When
        boolean result = inventoryService.reserveInventory(productId, quantity);

        // Then
        assertFalse(result);
        verify(inventoryRepository, never()).save(any(InventoryItem.class));
    }

    @Test
    void releaseInventory_ShouldReleaseReservedQuantity_WhenValidRequest() {
        // Given
        String productId = "product-1";
        Integer quantity = 5;
        testInventoryItem.setReservedQuantity(5);
        testInventoryItem.setAvailableQuantity(95);
        
        when(inventoryRepository.findByProductId(productId)).thenReturn(Optional.of(testInventoryItem));
        when(inventoryRepository.save(any(InventoryItem.class))).thenReturn(testInventoryItem);

        // When
        inventoryService.releaseInventory(productId, quantity);

        // Then
        verify(inventoryRepository).save(argThat(item -> 
            item.getReservedQuantity() == 0 && item.getAvailableQuantity() == 100));
    }

    @Test
    void confirmInventory_ShouldReduceQuantity_WhenValidRequest() {
        // Given
        String productId = "product-1";
        Integer quantity = 5;
        testInventoryItem.setReservedQuantity(5);
        testInventoryItem.setAvailableQuantity(95);
        
        when(inventoryRepository.findByProductId(productId)).thenReturn(Optional.of(testInventoryItem));
        when(inventoryRepository.save(any(InventoryItem.class))).thenReturn(testInventoryItem);

        // When
        inventoryService.confirmInventory(productId, quantity);

        // Then
        verify(inventoryRepository).save(argThat(item -> 
            item.getQuantity() == 95 && item.getReservedQuantity() == 0));
    }

    @Test
    void handleOrderCreated_ShouldProcessAllItems_WhenValidOrder() {
        // Given
        when(inventoryRepository.findByProductId(anyString())).thenReturn(Optional.of(testInventoryItem));
        when(inventoryRepository.save(any(InventoryItem.class))).thenReturn(testInventoryItem);

        // When
        inventoryService.handleOrderCreated(orderCreatedEvent);

        // Then
        verify(inventoryRepository).save(any(InventoryItem.class));
        verify(kafkaTemplate).send(anyString(), any());
    }

    @Test
    void updateStock_ShouldUpdateQuantity_WhenValidRequest() {
        // Given
        String productId = "product-1";
        Integer newQuantity = 200;
        when(inventoryRepository.findByProductId(productId)).thenReturn(Optional.of(testInventoryItem));
        when(inventoryRepository.save(any(InventoryItem.class))).thenReturn(testInventoryItem);

        // When
        inventoryService.updateStock(productId, newQuantity);

        // Then
        verify(inventoryRepository).save(argThat(item -> 
            item.getQuantity() == 200 && item.getAvailableQuantity() == 200));
    }
}
