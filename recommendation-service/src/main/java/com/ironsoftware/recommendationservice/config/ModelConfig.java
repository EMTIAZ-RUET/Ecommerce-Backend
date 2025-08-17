package com.ironsoftware.recommendationservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "recommendation.model")
public class ModelConfig {
    private int minDataPoints = 1000;
    private long retrainInterval = 3600000; // 1 hour in milliseconds
    private int maxRecommendations = 10;
    private double learningRate = 0.01;
    private int iterations = 10;
}
