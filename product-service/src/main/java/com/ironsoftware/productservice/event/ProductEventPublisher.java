package com.ironsoftware.productservice.event;

import com.ironsoftware.common.events.channels.EcommerceChannels;
import com.ironsoftware.productservice.model.Product;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductEventPublisher {
    
    private static final Logger log = LoggerFactory.getLogger(ProductEventPublisher.class);

    private final KafkaTemplate<String, ProductEvent> kafkaTemplate;
    
    public void publishProductCreatedEvent(Product product) {
        ProductCreatedEvent event = new ProductCreatedEvent(product);
        log.info("Publishing product created event for product ID: {} to topic: {}",
                product.getId(), EcommerceChannels.PRODUCT_EVENTS);
        kafkaTemplate.send(EcommerceChannels.PRODUCT_EVENTS, product.getId(), event);
    }
    
    public void publishProductUpdatedEvent(Product product) {
        ProductUpdatedEvent event = new ProductUpdatedEvent(product);
        log.info("Publishing product updated event for product ID: {} to topic: {}",
                product.getId(), EcommerceChannels.PRODUCT_EVENTS);
        kafkaTemplate.send(EcommerceChannels.PRODUCT_EVENTS, product.getId(), event);
    }
    
    public void publishProductDeletedEvent(Product product) {
        ProductDeletedEvent event = new ProductDeletedEvent(product);
        log.info("Publishing product deleted event for product ID: {} to topic: {}",
                product.getId(), EcommerceChannels.PRODUCT_EVENTS);
        kafkaTemplate.send(EcommerceChannels.PRODUCT_EVENTS, product.getId(), event);
    }
    
    public void publishStockUpdatedEvent(Product product, Integer previousStock) {
        ProductStockUpdatedEvent event = ProductStockUpdatedEvent.builder()
                .product(product)
                .previousStock(previousStock)
                .currentStock(product.getStock())
                .build();
        log.info("Publishing stock updated event for product ID: {} from {} to {} on topic: {}",
                product.getId(), previousStock, product.getStock(), EcommerceChannels.INVENTORY_EVENTS);
        kafkaTemplate.send(EcommerceChannels.INVENTORY_EVENTS, product.getId(), event);
    }
}