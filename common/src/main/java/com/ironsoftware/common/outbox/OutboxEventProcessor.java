package com.ironsoftware.common.outbox;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironsoftware.common.kafka.EventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class OutboxEventProcessor {

    private final OutboxEventRepository outboxEventRepository;
    private final EventPublisher eventPublisher;
    private final ObjectMapper objectMapper;

    @Scheduled(fixedRate = 1000)
    @Transactional
    public void processOutboxEvents() {
        List<OutboxEvent> unprocessedEvents = outboxEventRepository
            .findByProcessedFalseOrderByCreatedAtAsc();

        for (OutboxEvent event : unprocessedEvents) {
            try {
                // Publish event to Kafka
                eventPublisher.publishEvent(
                    event.getEventType(),
                    event.getAggregateId(),
                    objectMapper.readValue(event.getPayload(), Object.class)
                ).get(); // Wait for completion

                // Mark as processed
                event.setProcessed(true);
                event.setProcessedAt(Instant.now());
                outboxEventRepository.save(event);

                log.info("Successfully processed outbox event: {}", event.getId());
            } catch (Exception e) {
                log.error("Failed to process outbox event: {}", event.getId(), e);
                // Event will be retried in the next scheduling cycle
            }
        }
    }
}
