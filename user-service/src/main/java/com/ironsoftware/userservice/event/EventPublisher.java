package com.ironsoftware.userservice.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishUserRegisteredEvent(UserRegisteredEvent event) {
        String topic = "user-registered";
        String key = event.getUserId();
        
        log.info("Publishing user registered event for user: {}", event.getUserId());
        
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, key, event);
        
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("User registered event sent successfully for user: {}, offset: {}", 
                        event.getUserId(), result.getRecordMetadata().offset());
            } else {
                log.error("Unable to send user registered event for user: {}", event.getUserId(), ex);
            }
        });
    }

    public void publishUserProfileUpdatedEvent(UserProfileUpdatedEvent event) {
        String topic = "user-profile-updated";
        String key = event.getUserId();
        
        log.info("Publishing user profile updated event for user: {}", event.getUserId());
        
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, key, event);
        
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("User profile updated event sent successfully for user: {}, offset: {}", 
                        event.getUserId(), result.getRecordMetadata().offset());
            } else {
                log.error("Unable to send user profile updated event for user: {}", event.getUserId(), ex);
            }
        });
    }

    public void publishUserEvent(UserEvent event) {
        String topic = event.getEventType();
        String key = event.getUserId();
        
        log.info("Publishing {} event for user: {}", event.getEventType(), event.getUserId());
        
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, key, event);
        
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("{} event sent successfully for user: {}, offset: {}", 
                        event.getEventType(), event.getUserId(), result.getRecordMetadata().offset());
            } else {
                log.error("Unable to send {} event for user: {}", event.getEventType(), event.getUserId(), ex);
            }
        });
    }
}