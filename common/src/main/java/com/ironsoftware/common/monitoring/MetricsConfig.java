package com.ironsoftware.common.monitoring;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class MetricsConfig {

    @Bean
    public TimedAspect timedAspect(MeterRegistry registry) {
        return new TimedAspect(registry);
    }

    @Bean
    public CustomMetricsTags customMetricsTags() {
        return new CustomMetricsTags(
            Arrays.asList(
                Tag.of("environment", "${spring.profiles.active:unknown}"),
                Tag.of("application", "${spring.application.name}")
            )
        );
    }
}
