package com.ironsoftware.common.event;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Document(collection = "processed_events")
public class ProcessedEvent {
    @Id
    private String id;
    
    @Indexed(unique = true)
    private String eventId;
    
    private String eventType;
    private Instant processedAt;
    private String serviceId;
}
