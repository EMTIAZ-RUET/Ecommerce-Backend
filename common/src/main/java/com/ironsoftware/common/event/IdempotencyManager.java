package com.ironsoftware.common.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class IdempotencyManager {
    private final ProcessedEventRepository processedEventRepository;

    @Transactional
    public boolean processIfNotProcessed(String eventId, String eventType, String serviceId, Runnable processor) {
        if (!processedEventRepository.existsByEventId(eventId)) {
            try {
                processor.run();
                saveProcessedEvent(eventId, eventType, serviceId);
                return true;
            } catch (Exception e) {
                throw new EventProcessingException("Failed to process event: " + eventId, e);
            }
        }
        return false;
    }

    private void saveProcessedEvent(String eventId, String eventType, String serviceId) {
        ProcessedEvent processedEvent = new ProcessedEvent();
        processedEvent.setEventId(eventId);
        processedEvent.setEventType(eventType);
        processedEvent.setServiceId(serviceId);
        processedEvent.setProcessedAt(Instant.now());
        processedEventRepository.save(processedEvent);
    }
}
