package com.ironsoftware.recommendationservice.service;

import com.ironsoftware.recommendationservice.dto.ProductRecommendationDto;
import com.ironsoftware.recommendationservice.model.UserPreference;
import com.ironsoftware.recommendationservice.repository.UserPreferenceRepository;
import com.ironsoftware.common.event.UserActivityEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.spark.ml.recommendation.ALS;
import org.apache.spark.ml.recommendation.ALSModel;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecommendationService {
    private final UserPreferenceRepository preferenceRepository;
    private final ModelTrainingService modelTrainingService;

    @Cacheable(value = "recommendations", key = "#userId")
    public List<ProductRecommendationDto> getRecommendationsForUser(String userId) {
        return modelTrainingService.predictForUser(userId);
    }

    @KafkaListener(topics = "user-activity", groupId = "recommendation-group")
    public void handleUserActivity(UserActivityEvent event) {
        UserPreference preference = UserPreference.builder()
            .userId(event.getUserId())
            .productId(event.getProductId())
            .rating(calculateRating(event))
            .timestamp(event.getTimestamp().toEpochMilli())
            .build();

        preferenceRepository.save(preference);
        modelTrainingService.scheduleTrainingIfNeeded();
    }

    private double calculateRating(UserActivityEvent event) {
        // Implement rating calculation based on user activity type
        switch (event.getEventType()) {
            case "PURCHASE":
                return 5.0;
            case "VIEW":
                return 3.0;
            case "CART_ADD":
                return 4.0;
            default:
                return 1.0;
        }
    }
}
