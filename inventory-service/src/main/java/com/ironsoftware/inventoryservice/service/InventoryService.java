package com.ironsoftware.inventoryservice.service;

import com.ironsoftware.common.events.order.OrderCreatedEvent;
import com.ironsoftware.inventoryservice.dto.InventoryResponse;
import com.ironsoftware.inventoryservice.model.InventoryItem;
import com.ironsoftware.inventoryservice.model.InventoryStatus;
import com.ironsoftware.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    @Cacheable(value = "inventory", key = "#productId")
    public InventoryResponse checkInventory(String productId) {
        InventoryItem item = inventoryRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found in inventory"));
        
        return buildInventoryResponse(item);
    }

    @Transactional
    @CacheEvict(value = "inventory", key = "#item.productId")
    public InventoryResponse updateInventory(InventoryItem item) {
        updateInventoryStatus(item);
        return buildInventoryResponse(inventoryRepository.save(item));
    }

    @Transactional
    @KafkaListener(topics = "order-confirmed", groupId = "inventory-group")
    public void handleOrderConfirmed(OrderCreatedEvent event) {
        // Convert reserved stock to sold when order is confirmed
        event.getItems().forEach(orderItem -> {
            InventoryItem item = inventoryRepository.findById(orderItem.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found in inventory: " + orderItem.getProductId()));

            // Reduce reserved quantity (stock was already reserved during order creation)
            int currentReserved = item.getReservedQuantity();
            int newReserved = Math.max(0, currentReserved - orderItem.getQuantity());
            item.setReservedQuantity(newReserved);
            
            updateInventoryStatus(item);
            inventoryRepository.save(item);
            
            log.info("Confirmed inventory for product: {}, reserved quantity reduced to: {}",
                    orderItem.getProductId(), item.getReservedQuantity());
        });
    }
    
    @Transactional
    @KafkaListener(topics = "order-cancelled", groupId = "inventory-group")
    public void handleOrderCancelled(OrderCreatedEvent event) {
        // Release reserved stock when order is cancelled
        event.getItems().forEach(orderItem -> {
            try {
                releaseReservation(orderItem.getProductId(), orderItem.getQuantity());
                log.info("Released inventory reservation for cancelled order, product: {}", orderItem.getProductId());
            } catch (Exception e) {
                log.error("Failed to release inventory reservation for product: {}", orderItem.getProductId(), e);
            }
        });
    }

    @Transactional(readOnly = true)
    public List<InventoryResponse> checkInventoryBatch(List<String> productIds) {
        return inventoryRepository.findByProductIdIn(productIds).stream()
                .map(this::buildInventoryResponse)
                .collect(Collectors.toList());
    }

    private void updateInventoryStatus(InventoryItem item) {
        int availableQuantity = item.getQuantity() - item.getReservedQuantity();
        
        if (availableQuantity <= 0) {
            item.setStatus(InventoryStatus.OUT_OF_STOCK);
        } else if (availableQuantity <= item.getReorderPoint()) {
            item.setStatus(InventoryStatus.LOW_STOCK);
        } else {
            item.setStatus(InventoryStatus.IN_STOCK);
        }
    }

    private InventoryResponse buildInventoryResponse(InventoryItem item) {
        int availableQuantity = item.getQuantity() - item.getReservedQuantity();
        
        return InventoryResponse.builder()
                .productId(item.getProductId())
                .availableQuantity(availableQuantity)
                .status(item.getStatus())
                .isAvailable(availableQuantity > 0)
                .build();
    }

    @Transactional(readOnly = true)
    public boolean checkAvailability(String productId, int quantity) {
        InventoryItem item = inventoryRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found in inventory: " + productId));
        
        int availableQuantity = item.getQuantity() - item.getReservedQuantity();
        return availableQuantity >= quantity;
    }

    @Transactional
    @CacheEvict(value = "inventory", key = "#productId")
    public void reserveStock(String productId, int quantity) {
        InventoryItem item = inventoryRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found in inventory: " + productId));
        
        int availableQuantity = item.getQuantity() - item.getReservedQuantity();
        if (availableQuantity < quantity) {
            throw new RuntimeException("Insufficient inventory for product: " + productId + 
                    ". Available: " + availableQuantity + ", Requested: " + quantity);
        }
        
        // Reserve stock by increasing reserved quantity
        item.setReservedQuantity(item.getReservedQuantity() + quantity);
        updateInventoryStatus(item);
        inventoryRepository.save(item);
        
        log.info("Reserved {} units for product: {}, total reserved: {}", 
                quantity, productId, item.getReservedQuantity());
    }

    @Transactional
    @CacheEvict(value = "inventory", key = "#productId")
    public void releaseReservation(String productId, int quantity) {
        InventoryItem item = inventoryRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found in inventory: " + productId));
        
        int currentReserved = item.getReservedQuantity();
        int newReserved = Math.max(0, currentReserved - quantity);
        item.setReservedQuantity(newReserved);
        
        updateInventoryStatus(item);
        inventoryRepository.save(item);
        
        log.info("Released {} units for product: {}, total reserved: {}", 
                quantity, productId, item.getReservedQuantity());
    }
}
