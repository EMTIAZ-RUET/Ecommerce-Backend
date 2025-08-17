package com.ironsoftware.auditservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.Instant;
import java.util.Map;

@Document(collection = "audit_events")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditEvent {
    @Id
    private String id;
    
    private String userId;
    private String sessionId;
    private String entityType;
    private String entityId;
    private String action;
    
    @Enumerated(EnumType.STRING)
    private AuditEventType eventType;
    
    private String serviceName;
    private String ipAddress;
    private String userAgent;
    
    private Map<String, Object> oldValues;
    private Map<String, Object> newValues;
    private Map<String, Object> metadata;
    
    private String result; // SUCCESS, FAILURE
    private String errorMessage;
    
    @CreatedDate
    private Instant timestamp;
}
