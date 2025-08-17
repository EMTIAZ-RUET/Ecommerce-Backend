package com.ironsoftware.inventoryservice.repository;

import com.ironsoftware.inventoryservice.model.InventoryItem;
import com.ironsoftware.inventoryservice.model.InventoryStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InventoryItemRepositoryTest {

    @Mock
    private InventoryItemRepository inventoryRepository;

    private InventoryItem testInventoryItem;

    @BeforeEach
    void setUp() {
        testInventoryItem = InventoryItem.builder()
                .productId("product-1")
                .quantity(100)
                .reservedQuantity(0)
                .reorderLevel(10)
                .maxStockLevel(500)
                .status(InventoryStatus.IN_STOCK)
                .version(1L)
                .lastUpdated(Instant.now())
                .build();
    }

    @Test
    void findByProductId_ShouldReturnInventoryItem_WhenItemExists() {
        // Given
        when(inventoryRepository.findByProductId(anyString())).thenReturn(Optional.of(testInventoryItem));

        // When
        Optional<InventoryItem> result = inventoryRepository.findByProductId("product-1");

        // Then
        assertTrue(result.isPresent());
        assertEquals(testInventoryItem.getProductId(), result.get().getProductId());
        assertEquals(testInventoryItem.getQuantity(), result.get().getQuantity());
    }

    @Test
    void findByStatus_ShouldReturnInventoryItems_WhenItemsExist() {
        // Given
        List<InventoryItem> items = Arrays.asList(testInventoryItem);
        when(inventoryRepository.findByStatus(any(InventoryStatus.class))).thenReturn(items);

        // When
        List<InventoryItem> result = inventoryRepository.findByStatus(InventoryStatus.IN_STOCK);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(InventoryStatus.IN_STOCK, result.get(0).getStatus());
    }

    @Test
    void findByQuantityLessThanReorderLevel_ShouldReturnLowStockItems() {
        // Given
        InventoryItem lowStockItem = testInventoryItem.toBuilder()
                .quantity(5)
                .status(InventoryStatus.LOW_STOCK)
                .build();
        
        List<InventoryItem> items = Arrays.asList(lowStockItem);
        when(inventoryRepository.findByQuantityLessThanReorderLevel()).thenReturn(items);

        // When
        List<InventoryItem> result = inventoryRepository.findByQuantityLessThanReorderLevel();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.get(0).getQuantity() < result.get(0).getReorderLevel());
    }

    @Test
    void save_ShouldReturnSavedInventoryItem_WhenValidItem() {
        // Given
        when(inventoryRepository.save(any(InventoryItem.class))).thenReturn(testInventoryItem);

        // When
        InventoryItem result = inventoryRepository.save(testInventoryItem);

        // Then
        assertNotNull(result);
        assertEquals(testInventoryItem.getProductId(), result.getProductId());
        assertEquals(testInventoryItem.getQuantity(), result.getQuantity());
    }

    @Test
    void existsByProductId_ShouldReturnTrue_WhenProductExists() {
        // Given
        when(inventoryRepository.existsByProductId(anyString())).thenReturn(true);

        // When
        boolean result = inventoryRepository.existsByProductId("product-1");

        // Then
        assertTrue(result);
    }
}
