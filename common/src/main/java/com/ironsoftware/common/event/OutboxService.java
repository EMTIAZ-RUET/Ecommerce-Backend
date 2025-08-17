package com.ironsoftware.common.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class OutboxService {
    
    private final OutboxEventRepository outboxEventRepository;
    
    @Transactional
    public OutboxEvent createOutboxEvent(String eventType, String aggregateId, String payload) {
        OutboxEvent outboxEvent = new OutboxEvent();
        outboxEvent.setEventType(eventType);
        outboxEvent.setAggregateId(aggregateId);
        outboxEvent.setAggregateType(eventType.replace("Event", ""));
        outboxEvent.setPayload(payload);
        outboxEvent.setCreatedAt(Instant.now());
        outboxEvent.setProcessed(false);
        
        return outboxEventRepository.save(outboxEvent);
    }
    
    @Transactional
    public void markAsProcessed(Long eventId) {
        OutboxEvent event = outboxEventRepository.findById(eventId)
            .orElseThrow(() -> new RuntimeException("Event not found: " + eventId));
        
        event.setProcessed(true);
        event.setProcessedAt(Instant.now());
        outboxEventRepository.save(event);
    }
}