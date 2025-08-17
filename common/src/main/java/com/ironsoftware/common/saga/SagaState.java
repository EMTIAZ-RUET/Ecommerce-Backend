package com.ironsoftware.common.saga;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SagaState {
    private String sagaId;
    private String orderId;
    private SagaStatus status;
    private Map<String, StepStatus> stepStatus;
    private String currentStep;
    private Instant startTime;
    private Instant endTime;
    private String errorMessage;

    public enum SagaStatus {
        STARTED,
        IN_PROGRESS,
        COMPLETED,
        FAILED,
        COMPENSATING,
        COMPENSATED
    }

    public enum StepStatus {
        PENDING,
        COMPLETED,
        FAILED,
        COMPENSATING,
        COMPENSATED
    }
}
