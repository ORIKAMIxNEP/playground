package com.spring_boot_template.domain.exception;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title = "リソース競合例外")
public final class ResourceConflictException extends RuntimeException {
    public ResourceConflictException(final String message) {
        super(message);
    }
}
