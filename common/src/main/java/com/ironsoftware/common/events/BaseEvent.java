package com.ironsoftware.common.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseEvent {
    private String eventId;
    private String eventType;
    private Instant timestamp;
    private String source;
    private String version;
}
