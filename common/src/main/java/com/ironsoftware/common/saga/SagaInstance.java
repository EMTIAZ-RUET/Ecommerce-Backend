package com.ironsoftware.common.saga;

import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import org.springframework.data.annotation.Id;
import java.time.Instant;

@Document(collection = "saga_instances")
@Data
public class SagaInstance {
    @Id
    private String id;
    private String sagaId;
    private String sagaType;
    private SagaState sagaState;
    private Instant createdAt;
    private Instant updatedAt;
}
