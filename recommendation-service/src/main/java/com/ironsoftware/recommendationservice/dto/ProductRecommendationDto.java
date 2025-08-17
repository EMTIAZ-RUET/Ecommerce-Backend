package com.ironsoftware.recommendationservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductRecommendationDto {
    private String productId;
    private double score;
}
