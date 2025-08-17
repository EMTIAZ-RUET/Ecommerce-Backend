package com.ironsoftware.analyticsservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Map;

@Document(collection = "analytics_events")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnalyticsEvent {
    @Id
    private String id;
    
    private String userId;
    private String sessionId;
    private String eventType;
    private String eventName;
    private String source;
    private String category;
    
    private Map<String, Object> properties;
    private Map<String, Object> userProperties;
    private Map<String, Object> deviceInfo;
    
    private String ipAddress;
    private String userAgent;
    private String referrer;
    private String page;
    
    @CreatedDate
    private Instant timestamp;
    
    private Instant processedAt;
}
