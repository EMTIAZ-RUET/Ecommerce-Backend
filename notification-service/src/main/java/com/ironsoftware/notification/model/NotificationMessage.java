package com.ironsoftware.notification.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationMessage {
    private String id;
    private String userId;
    private String title;
    private String message;
    private NotificationType type;
    private LocalDateTime timestamp;
    private boolean read;
    
    public enum NotificationType {
        INFO,
        WARNING,
        ERROR,
        SUCCESS
    }
}