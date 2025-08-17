package com.ironsoftware.common.events.channels;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Interface defining the event channels for the e-commerce application.
 * This uses Spring Cloud Stream 4.x functional programming model.
 */
public interface EcommerceChannels {
    // Kafka Topic names as constants
    String ORDER_EVENTS = "order-events";
    String PAYMENT_EVENTS = "payment-events";
    String INVENTORY_EVENTS = "inventory-events";
    String DELIVERY_EVENTS = "delivery-events";
    String PRODUCT_EVENTS = "product-events";
    String USER_EVENTS = "user-events";
    String NOTIFICATION_EVENTS = "notification-events";
    
    // Channel binding names for Spring Cloud Stream
    String ORDER_CREATED_BINDING = "orderCreated";
    String PAYMENT_PROCESSED_BINDING = "paymentProcessed";
    String INVENTORY_UPDATED_BINDING = "inventoryUpdated";
    String DELIVERY_CREATED_BINDING = "deliveryCreated";
    String DELIVERY_STATUS_UPDATED_BINDING = "deliveryStatusUpdated";
    String PRODUCT_CREATED_BINDING = "productCreated";
    String PRODUCT_UPDATED_BINDING = "productUpdated";
    
    /**
     * Consumer function for order created events
     */
    @Bean
    default Consumer<Message<?>> orderCreatedConsumer() {
        return message -> {
            // Implementation to be provided by services
        };
    }
    
    /**
     * Consumer function for payment processed events
     */
    @Bean
    default Consumer<Message<?>> paymentProcessedConsumer() {
        return message -> {
            // Implementation to be provided by services
        };
    }
    
    /**
     * Consumer function for inventory updated events
     */
    @Bean
    default Consumer<Message<?>> inventoryUpdatedConsumer() {
        return message -> {
            // Implementation to be provided by services
        };
    }
    
    /**
     * Consumer function for delivery created events
     */
    @Bean
    default Consumer<Message<?>> deliveryCreatedConsumer() {
        return message -> {
            // Implementation to be provided by services
        };
    }
    
    /**
     * Consumer function for delivery status updated events
     */
    @Bean
    default Consumer<Message<?>> deliveryStatusUpdatedConsumer() {
        return message -> {
            // Implementation to be provided by services
        };
    }
}
