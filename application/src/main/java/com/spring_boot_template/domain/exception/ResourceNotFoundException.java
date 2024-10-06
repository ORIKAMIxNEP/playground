package com.spring_boot_template.domain.exception;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title = "リソース未検出例外")
public final class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(final String message) {
        super(message);
    }
}
