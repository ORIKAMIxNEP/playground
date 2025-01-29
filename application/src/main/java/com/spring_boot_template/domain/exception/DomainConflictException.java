package com.spring_boot_template.domain.exception;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title = "ドメイン競合例外")
public final class DomainConflictException extends RuntimeException {
    public DomainConflictException(final String message) {
        super(message);
    }
}
