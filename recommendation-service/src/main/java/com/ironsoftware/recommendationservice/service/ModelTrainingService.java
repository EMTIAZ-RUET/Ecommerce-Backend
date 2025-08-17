package com.ironsoftware.recommendationservice.service;

import com.ironsoftware.recommendationservice.config.ModelConfig;
import com.ironsoftware.recommendationservice.dto.ProductRecommendationDto;
import com.ironsoftware.recommendationservice.model.UserPreference;
import com.ironsoftware.recommendationservice.repository.UserPreferenceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.spark.ml.recommendation.ALS;
import org.apache.spark.ml.recommendation.ALSModel;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ModelTrainingService {
    private final SparkSession sparkSession;
    private final UserPreferenceRepository preferenceRepository;
    private final ModelConfig modelConfig;
    private volatile ALSModel currentModel;

    @Scheduled(fixedRateString = "${recommendation.model.retrain-interval}")
    public void trainModel() {
        log.info("Starting model training");
        
        Dataset<Row> trainingData = sparkSession.createDataFrame(
            preferenceRepository.findAll(),
            UserPreference.class
        );

        ALS als = new ALS()
            .setMaxIter(10)
            .setRegParam(0.01)
            .setUserCol("userId")
            .setItemCol("productId")
            .setRatingCol("rating");

        currentModel = als.fit(trainingData);
        
        log.info("Model training completed");
    }

    public List<ProductRecommendationDto> predictForUser(String userId) {
        if (currentModel == null) {
            trainModel();
        }

        Dataset<Row> userProducts = currentModel.recommendForUserSubset(
            sparkSession.createDataFrame(
                Collections.singletonList(userId),
                String.class
            ),
            10
        );

        return convertToRecommendations(userProducts);
    }

    private List<ProductRecommendationDto> convertToRecommendations(Dataset<Row> predictions) {
        // Convert Spark predictions to DTOs
        return predictions.collectAsList().stream()
            .map(row -> ProductRecommendationDto.builder()
                .productId(row.getString(0))
                .score(row.getDouble(1))
                .build())
            .collect(Collectors.toList());
    }

    public void scheduleTrainingIfNeeded() {
        // Implement logic to determine if retraining is needed
        if (isRetrainingNeeded()) {
            trainModel();
        }
    }

    private boolean isRetrainingNeeded() {
        return preferenceRepository.count() % modelConfig.getMinDataPoints() == 0;
    }
}
