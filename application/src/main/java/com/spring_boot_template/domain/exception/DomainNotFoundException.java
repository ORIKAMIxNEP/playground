package com.spring_boot_template.domain.exception;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title = "ドメイン未発見例外")
public final class DomainNotFoundException extends RuntimeException {
    public DomainNotFoundException(final String message) {
        super(message);
    }
}
