package com.spring_boot_template.domain.exception;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title = "ドメイン知識例外")
public final class DomainKnowledgeException extends RuntimeException {
    public DomainKnowledgeException(final String message) {
        super(message);
    }
}
