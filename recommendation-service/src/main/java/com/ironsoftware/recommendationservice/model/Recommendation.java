package com.ironsoftware.recommendationservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Document(collection = "recommendations")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Recommendation {
    @Id
    private String id;
    
    private String userId;
    private String productId;
    private String sessionId;
    
    @Enumerated(EnumType.STRING)
    private RecommendationType type;
    
    private Double score;
    private String algorithm;
    private Map<String, Object> features;
    private List<String> reasons;
    
    private boolean clicked;
    private boolean purchased;
    private Integer position;
    
    @CreatedDate
    private Instant createdAt;
    
    @LastModifiedDate
    private Instant updatedAt;
    
    private Instant expiresAt;
}
