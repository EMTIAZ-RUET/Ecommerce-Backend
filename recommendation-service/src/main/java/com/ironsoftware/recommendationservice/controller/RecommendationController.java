package com.ironsoftware.recommendationservice.controller;

import com.ironsoftware.recommendationservice.dto.ProductRecommendationDto;
import com.ironsoftware.recommendationservice.service.ModelTrainingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recommendations")
@RequiredArgsConstructor
public class RecommendationController {
    private final ModelTrainingService modelTrainingService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ProductRecommendationDto>> getRecommendationsForUser(
            @PathVariable String userId) {
        return ResponseEntity.ok(modelTrainingService.predictForUser(userId));
    }

    @PostMapping("/retrain")
    public ResponseEntity<Void> retrainModel() {
        modelTrainingService.trainModel();
        return ResponseEntity.ok().build();
    }
}
