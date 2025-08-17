package com.ironsoftware.common.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaHealthIndicator implements HealthIndicator {
    private final KafkaTemplate<String, ?> kafkaTemplate;

    public KafkaHealthIndicator(KafkaTemplate<String, ?> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public Health health() {
        try {
            kafkaTemplate.metrics();
            return Health.up()
                    .withDetail("brokers", kafkaTemplate.getDefaultTopic())
                    .build();
        } catch (Exception e) {
            return Health.down()
                    .withException(e)
                    .build();
        }
    }
}
