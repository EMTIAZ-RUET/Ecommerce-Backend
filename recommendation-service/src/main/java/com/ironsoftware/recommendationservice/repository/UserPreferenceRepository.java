package com.ironsoftware.recommendationservice.repository;

import com.ironsoftware.recommendationservice.model.UserPreference;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPreferenceRepository extends MongoRepository<UserPreference, String> {
    List<UserPreference> findByUserId(String userId);
    List<UserPreference> findByProductId(String productId);
    List<UserPreference> findByUserIdAndProductId(String userId, String productId);
}
