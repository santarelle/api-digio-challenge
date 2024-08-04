package com.digio.challenge.application.exception;

public class RecommendedWineNotFoundException extends RuntimeException {
    public RecommendedWineNotFoundException(String message) {
        super(message);
    }
}
