package com.ironsoftware.common.outbox;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironsoftware.common.kafka.EventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OutboxProcessor {
    private final OutboxRepository outboxRepository;
    private final EventPublisher eventPublisher;
    private final ObjectMapper objectMapper;

    @Scheduled(fixedRate = 5000)
    @Transactional
    public void processOutbox() {
        List<OutboxMessage> unpublishedMessages = outboxRepository
            .findByPublishedFalseAndRetryCountLessThan(3);

        for (OutboxMessage message : unpublishedMessages) {
            try {
                eventPublisher.publishEvent(
                    message.getEventType(),
                    message.getAggregateId(),
                    objectMapper.readValue(message.getPayload(), Object.class)
                ).get(); // Wait for completion

                message.setPublished(true);
                message.setPublishedAt(Instant.now());
                outboxRepository.save(message);
            } catch (Exception e) {
                message.setRetryCount(message.getRetryCount() + 1);
                outboxRepository.save(message);
            }
        }
    }
}
