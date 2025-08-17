package com.ironsoftware.common.saga;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SagaInstanceRepository extends JpaRepository<SagaInstance, String> {
    // Find saga instance by saga ID
    SagaInstance findBySagaId(String sagaId);
    
    // Find saga instances by order ID
    SagaInstance findBySagaState_OrderId(String orderId);
    
    // Find saga instances by saga type
    java.util.List<SagaInstance> findBySagaType(String sagaType);
    
    // Find saga instances by status
    java.util.List<SagaInstance> findBySagaState_Status(SagaState.SagaStatus status);
}