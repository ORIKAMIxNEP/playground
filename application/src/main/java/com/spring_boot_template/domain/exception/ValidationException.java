package com.spring_boot_template.domain.exception;

public final class ValidationException extends RuntimeException {
    public ValidationException(final String message) {
        super(message);
    }
}
