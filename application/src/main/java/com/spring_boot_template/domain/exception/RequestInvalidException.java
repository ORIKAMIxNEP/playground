package com.spring_boot_template.domain.exception;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title = "リクエスト無効例外")
public final class RequestInvalidException extends RuntimeException {
    public RequestInvalidException(final String message) {
        super(message);
    }
}
