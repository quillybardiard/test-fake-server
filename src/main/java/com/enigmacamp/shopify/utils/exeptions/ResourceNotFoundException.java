package com.enigmacamp.shopify.utils.exeptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
