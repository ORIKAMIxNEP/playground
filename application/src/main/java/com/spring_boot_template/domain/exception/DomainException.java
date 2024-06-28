package com.spring_boot_template.domain.exception;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title = "ドメイン知識例外")
public final class DomainException extends RuntimeException {
    public DomainException(final String message) {
        super(message);
    }
}
