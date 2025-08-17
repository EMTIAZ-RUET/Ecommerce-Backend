package com.ironsoftware.common.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventPublisher {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final OutboxService outboxService;

    @Retry(name = "eventPublisher")
    public CompletableFuture<SendResult<String, Object>> publishEvent(String topic, String key, Object event) {
        try {
            // Store in outbox first
            OutboxEvent outboxEvent = outboxService.createOutboxEvent(
                event.getClass().getSimpleName(),
                key,
                objectMapper.writeValueAsString(event)
            );

            return kafkaTemplate.send(topic, key, event)
                .whenComplete((result, ex) -> {
                    if (ex == null) {
                        outboxService.markAsProcessed(outboxEvent.getId());
                    } else {
                        log.error("Failed to publish event to topic {}: {}", topic, ex.getMessage());
                        // Event will be picked up by outbox processor
                    }
                });
        } catch (Exception e) {
            log.error("Failed to publish event: {}", e.getMessage());
            return CompletableFuture.failedFuture(e);
        }
    }

    public void publishEventWithFallback(String topic, String key, Object event, Runnable fallback) {
        try {
            publishEvent(topic, key, event).get();
        } catch (Exception e) {
            log.error("Event publishing failed, executing fallback for topic {}", topic);
            fallback.run();
        }
    }
}
