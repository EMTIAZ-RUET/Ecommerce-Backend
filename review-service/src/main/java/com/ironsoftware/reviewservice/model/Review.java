package com.ironsoftware.reviewservice.model;

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

@Document(collection = "reviews")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    private String id;
    
    private String productId;
    private String userId;
    private String orderId;
    private Integer rating; // 1-5 stars
    private String title;
    private String comment;
    private List<String> images;
    private List<String> tags;
    
    private boolean verified; // verified purchase
    private boolean helpful;
    private Integer helpfulCount;
    private Integer unhelpfulCount;
    
    @Enumerated(EnumType.STRING)
    private ReviewStatus status;
    
    private String moderatorId;
    private String moderationReason;
    
    @CreatedDate
    private Instant createdAt;
    
    @LastModifiedDate
    private Instant updatedAt;
}
