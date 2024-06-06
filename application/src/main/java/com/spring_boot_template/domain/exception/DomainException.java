package com.spring_boot_template.domain.exception;

public final class DomainException extends RuntimeException {
    public DomainException(final String message) {
        super(message);
    }
}
