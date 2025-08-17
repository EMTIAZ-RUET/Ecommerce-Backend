package com.ironsoftware.common.service;

import com.ironsoftware.common.cache.CacheConfig;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public abstract class BaseService {

    @CircuitBreaker(name = "default")
    @Retry(name = "default")
    @Bulkhead(name = "default")
    @RateLimiter(name = "default")
    public <T> T executeWithResilience(ResilienceOperation<T> operation) {
        return operation.execute();
    }

    @Cacheable(value = "defaultCache", key = "#key")
    protected <T> T executeWithCache(String key, CacheOperation<T> operation) {
        return operation.execute();
    }

    @FunctionalInterface
    public interface ResilienceOperation<T> {
        T execute();
    }

    @FunctionalInterface
    public interface CacheOperation<T> {
        T execute();
    }
}
