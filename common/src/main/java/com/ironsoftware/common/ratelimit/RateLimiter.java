package com.ironsoftware.common.ratelimit;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimiter {
    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();
    
    @Value("${rate-limit.tokens-per-minute:60}")
    private int tokensPerMinute;
    
    @Value("${rate-limit.tokens-per-second:2}")
    private int tokensPerSecond;
    
    private Bucket createBucket() {
        Bandwidth perMinute = Bandwidth.classic(tokensPerMinute, 
            Refill.intervally(tokensPerMinute, Duration.ofMinutes(1)));
        Bandwidth perSecond = Bandwidth.classic(tokensPerSecond, 
            Refill.intervally(tokensPerSecond, Duration.ofSeconds(1)));
        
        return Bucket4j.builder()
                .addLimit(perMinute)
                .addLimit(perSecond)
                .build();
    }
    
    public boolean tryConsume(HttpServletRequest request) {
        String key = getKey(request);
        Bucket bucket = buckets.computeIfAbsent(key, k -> createBucket());
        return bucket.tryConsume(1);
    }
    
    private String getKey(HttpServletRequest request) {
        // Use IP address or user ID for rate limiting
        String clientIp = request.getHeader("X-Forwarded-For");
        if (clientIp == null) {
            clientIp = request.getRemoteAddr();
        }
        return clientIp;
    }
}
