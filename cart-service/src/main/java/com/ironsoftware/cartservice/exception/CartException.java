package com.ironsoftware.cartservice.exception;

import org.springframework.http.HttpStatus;

public class CartException extends RuntimeException {
    private final HttpStatus status;
    private final String message;

    public CartException(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.status = status;
    }

    public CartException(String message) {
        this(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    public HttpStatus getStatus() {
        return status;
    }
    
    @Override
    public String getMessage() {
        return message;
    }
}
