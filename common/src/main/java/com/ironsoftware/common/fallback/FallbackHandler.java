package com.ironsoftware.common.fallback;

import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.function.Supplier;

@Component
public class FallbackHandler {
    private final CircuitBreakerFactory circuitBreakerFactory;

    public FallbackHandler(CircuitBreakerFactory circuitBreakerFactory) {
        this.circuitBreakerFactory = circuitBreakerFactory;
    }

    public <T> T executeWithFallback(String circuitName, 
                                   Supplier<T> primaryFunction,
                                   Function<Throwable, T> fallbackFunction) {
        return circuitBreakerFactory.create(circuitName)
                .run(primaryFunction::get, fallbackFunction);
    }
}
