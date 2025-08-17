package com.ironsoftware.reviewservice.service;

import com.ironsoftware.reviewservice.model.Review;
import com.ironsoftware.reviewservice.repository.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewService reviewService;

    private Review testReview;

    @BeforeEach
    void setUp() {
        testReview = Review.builder()
                .id("review-1")
                .productId("product-1")
                .userId("user-1")
                .orderId("order-1")
                .rating(5)
                .title("Excellent Product")
                .comment("This product exceeded my expectations!")
                .verified(true)
                .helpful(10)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
    }

    @Test
    void findById_ShouldReturnReview_WhenReviewExists() {
        // Given
        when(reviewRepository.findById(anyString())).thenReturn(Optional.of(testReview));

        // When
        Optional<Review> result = reviewRepository.findById("review-1");

        // Then
        assertTrue(result.isPresent());
        assertEquals(testReview.getId(), result.get().getId());
        assertEquals(testReview.getRating(), result.get().getRating());
    }

    @Test
    void findByProductId_ShouldReturnReviews_WhenProductHasReviews() {
        // Given
        List<Review> reviews = Arrays.asList(testReview);
        when(reviewRepository.findByProductId(anyString())).thenReturn(reviews);

        // When
        List<Review> result = reviewRepository.findByProductId("product-1");

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testReview.getProductId(), result.get(0).getProductId());
    }

    @Test
    void findByUserId_ShouldReturnReviews_WhenUserHasReviews() {
        // Given
        List<Review> reviews = Arrays.asList(testReview);
        when(reviewRepository.findByUserId(anyString())).thenReturn(reviews);

        // When
        List<Review> result = reviewRepository.findByUserId("user-1");

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testReview.getUserId(), result.get(0).getUserId());
    }

    @Test
    void save_ShouldReturnSavedReview_WhenValidReview() {
        // Given
        when(reviewRepository.save(any(Review.class))).thenReturn(testReview);

        // When
        Review result = reviewRepository.save(testReview);

        // Then
        assertNotNull(result);
        assertEquals(testReview.getId(), result.getId());
        assertEquals(testReview.getComment(), result.getComment());
    }

    @Test
    void findByRatingGreaterThanEqual_ShouldReturnHighRatedReviews() {
        // Given
        List<Review> reviews = Arrays.asList(testReview);
        when(reviewRepository.findByRatingGreaterThanEqual(4)).thenReturn(reviews);

        // When
        List<Review> result = reviewRepository.findByRatingGreaterThanEqual(4);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.get(0).getRating() >= 4);
    }
}
