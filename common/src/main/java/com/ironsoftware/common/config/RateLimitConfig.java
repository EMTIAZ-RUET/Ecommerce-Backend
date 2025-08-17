package com.ironsoftware.common.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class RateLimitConfig {

    public Bucket createNewBucket() {
        long tokens = 100; // Number of tokens per time window
        Refill refill = Refill.intervally(tokens, Duration.ofMinutes(1));
        Bandwidth limit = Bandwidth.classic(tokens, refill);
        return Bucket4j.builder().addLimit(limit).build();
    }
}
