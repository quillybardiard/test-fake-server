package com.enigmacamp.shopify.utils.exeptions;

public class ValidationException extends RuntimeException {
    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
