package com.ironsoftware.common.deadletter;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeadLetterPublisher {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final String DLQ_SUFFIX = ".dlq";

    public void publishToDlq(ConsumerRecord<String, Object> record, String originalTopic, Exception exception) {
        DeadLetterEvent event = new DeadLetterEvent(
            record.value(),
            originalTopic,
            exception.getMessage(),
            record.key(),
            record.timestamp()
        );

        kafkaTemplate.send(originalTopic + DLQ_SUFFIX, record.key(), event);
    }
}
