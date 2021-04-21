package com.spandigital.ddd.freelancer.domain.exception;

public final class EntityNotFoundException extends Exception {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
