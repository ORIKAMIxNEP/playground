package com.spring_boot_template.domain.exception;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title = "バリデーション例外")
public final class ValidationException extends RuntimeException {
    public ValidationException(final String message) {
        super(message);
    }
}
