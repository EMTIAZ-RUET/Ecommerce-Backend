package com.ironsoftware.cartservice.event;

import com.ironsoftware.cartservice.model.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class CartEventPublisher {
    private final KafkaTemplate<String, CartEvent> kafkaTemplate;
    private static final String TOPIC = "cart-events";

    public void publishCartEvent(CartEvent event) {
        kafkaTemplate.send(TOPIC, event.getCartId(), event);
    }
    
    public void publishCartUpdatedEvent(Cart cart) {
        CartEvent event = CartEvent.builder()
                .cartId(cart.getId())
                .userId(cart.getUserId())
                .eventType("CART_UPDATED")
                .timestamp(LocalDateTime.now())
                .build();
        publishCartEvent(event);
    }
    
    public void publishCartClearedEvent(Cart cart) {
        CartEvent event = CartEvent.builder()
                .cartId(cart.getId())
                .userId(cart.getUserId())
                .eventType("CART_CLEARED")
                .timestamp(LocalDateTime.now())
                .build();
        publishCartEvent(event);
    }
}
