package com.ironsoftware.cartservice.event;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartEventPublisher {
    private final KafkaTemplate<String, CartEvent> kafkaTemplate;
    private static final String TOPIC = "cart-events";

    public void publishCartEvent(CartEvent event) {
        kafkaTemplate.send(TOPIC, event.getCartId(), event);
    }
}
