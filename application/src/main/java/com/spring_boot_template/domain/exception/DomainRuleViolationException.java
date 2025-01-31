package com.spring_boot_template.domain.exception;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title = "ドメインルール違反例外")
public final class DomainRuleViolationException extends RuntimeException {
    public DomainRuleViolationException(final String message) {
        super(message);
    }
}
