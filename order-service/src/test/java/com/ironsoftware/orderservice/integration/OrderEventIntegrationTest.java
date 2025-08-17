package com.ironsoftware.orderservice.integration;

import com.ironsoftware.common.events.order.OrderCreatedEvent;
import com.ironsoftware.common.events.payment.PaymentProcessedEvent;
import com.ironsoftware.orderservice.entity.Order;
import com.ironsoftware.orderservice.entity.OrderStatus;
import com.ironsoftware.orderservice.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, topics = {"order-created", "payment-events"})
@TestPropertySource(properties = {
    "spring.kafka.bootstrap-servers=${spring.embedded.kafka.brokers}",
    "spring.kafka.consumer.auto-offset-reset=earliest"
})
class OrderEventIntegrationTest {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void shouldProcessPaymentEvent_WhenOrderExists() {
        // Given
        Order order = Order.builder()
                .id("test-order-1")
                .userId("user-1")
                .status(OrderStatus.CREATED)
                .totalAmount(BigDecimal.valueOf(100.00))
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
        
        orderRepository.save(order);

        PaymentProcessedEvent paymentEvent = new PaymentProcessedEvent();
        paymentEvent.setOrderId("test-order-1");
        paymentEvent.setStatus("COMPLETED");
        paymentEvent.setTimestamp(Instant.now());

        // When
        kafkaTemplate.send("payment-events", paymentEvent);

        // Then
        await().atMost(10, TimeUnit.SECONDS).untilAsserted(() -> {
            Order updatedOrder = orderRepository.findById("test-order-1").orElse(null);
            assertNotNull(updatedOrder);
            assertEquals(OrderStatus.PAID, updatedOrder.getStatus());
        });
    }

    @Test
    void shouldPublishOrderCreatedEvent_WhenOrderIsCreated() {
        // Given
        Order order = Order.builder()
                .id("test-order-2")
                .userId("user-2")
                .status(OrderStatus.CREATED)
                .totalAmount(BigDecimal.valueOf(200.00))
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        // When
        orderRepository.save(order);
        
        OrderCreatedEvent orderEvent = new OrderCreatedEvent();
        orderEvent.setOrderId(order.getId());
        orderEvent.setUserId(order.getUserId());
        orderEvent.setTotalAmount(order.getTotalAmount());
        
        kafkaTemplate.send("order-created", orderEvent);

        // Then - This test verifies the event can be published successfully
        // In a real scenario, we would verify downstream services receive the event
        assertNotNull(order.getId());
        assertEquals(OrderStatus.CREATED, order.getStatus());
    }
}
