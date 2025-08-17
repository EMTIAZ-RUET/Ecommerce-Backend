package com.ironsoftware.common.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserActivityEvent {
    private String userId;
    private String sessionId;
    private String eventType;
    private String productId;
    private String orderId;
    private Map<String, Object> properties;
    private Instant timestamp;
    private String source;
    private String userAgent;
    private String ipAddress;
}
