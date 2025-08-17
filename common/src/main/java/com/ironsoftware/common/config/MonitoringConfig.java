package com.ironsoftware.common.config;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;

@Configuration
public class MonitoringConfig {

    @Bean
    public MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        return registry -> registry.config().commonTags(
            "application", "ecommerce-microservice",
            "environment", "production"
        );
    }

    @Bean
    public Timer httpRequestTimer(MeterRegistry meterRegistry) {
        return Timer.builder("http.requests")
                .description("HTTP request duration")
                .register(meterRegistry);
    }
}
