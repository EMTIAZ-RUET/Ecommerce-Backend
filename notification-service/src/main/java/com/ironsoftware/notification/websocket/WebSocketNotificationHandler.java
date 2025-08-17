package com.ironsoftware.notification.websocket;

import com.ironsoftware.notification.model.NotificationMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class WebSocketNotificationHandler {

    private final SimpMessagingTemplate messagingTemplate;

    @KafkaListener(topics = "notifications", groupId = "websocket-notification-group")
    public void handleNotification(NotificationMessage notification) {
        String destination = String.format("/topic/notifications/%s", notification.getUserId());
        messagingTemplate.convertAndSend(destination, notification);
        log.info("Sent notification to {}: {}", destination, notification);
    }
}
