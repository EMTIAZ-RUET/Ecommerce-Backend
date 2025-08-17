package com.ironsoftware.common.events;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class BaseEventTest {

    @Test
    void baseEvent_ShouldCreateWithAllFields() {
        // Given
        String eventId = "event-123";
        String eventType = "TEST_EVENT";
        Instant timestamp = Instant.now();
        String source = "test-service";
        String version = "1.0";

        // When
        BaseEvent event = new BaseEvent(eventId, eventType, timestamp, source, version);

        // Then
        assertNotNull(event);
        assertEquals(eventId, event.getEventId());
        assertEquals(eventType, event.getEventType());
        assertEquals(timestamp, event.getTimestamp());
        assertEquals(source, event.getSource());
        assertEquals(version, event.getVersion());
    }

    @Test
    void baseEvent_ShouldCreateWithNoArgsConstructor() {
        // When
        BaseEvent event = new BaseEvent();

        // Then
        assertNotNull(event);
        assertNull(event.getEventId());
        assertNull(event.getEventType());
        assertNull(event.getTimestamp());
        assertNull(event.getSource());
        assertNull(event.getVersion());
    }
}
