package com.ironsoftware.common.event;

public class EventProcessingException extends RuntimeException {
    public EventProcessingException(String message, Throwable cause) {
        super(message, cause);
    }

    public EventProcessingException(String message) {
        super(message);
    }
}