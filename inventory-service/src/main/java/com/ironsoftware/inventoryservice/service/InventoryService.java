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
    @KafkaListener(topics = "order-created", groupId = "inventory-group")
    public void handleOrderCreated(OrderCreatedEvent event) {
        // Process each item in the order
        event.getItems().forEach(orderItem -> {
            InventoryItem item = inventoryRepository.findById(orderItem.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found in inventory: " + orderItem.getProductId()));

            if (item.getQuantity() < orderItem.getQuantity()) {
                throw new RuntimeException("Insufficient inventory for product: " + orderItem.getProductId());
            }

            item.setQuantity(item.getQuantity() - orderItem.getQuantity());
            item.setReservedQuantity(item.getReservedQuantity() + orderItem.getQuantity());
            
            updateInventoryStatus(item);
            inventoryRepository.save(item);
            
            log.info("Updated inventory for product: {}, remaining quantity: {}",
                    orderItem.getProductId(), item.getQuantity());
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
}
