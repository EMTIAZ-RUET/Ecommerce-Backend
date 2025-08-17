package com.ironsoftware.common.deadletter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeadLetterEvent {
    private Object originalPayload;
    private String originalTopic;
    private String errorMessage;
    private String key;
    private long timestamp;
}