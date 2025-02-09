package com.playground.domain.exception;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title = "ドメインルール違反例外")
public final class DomainRuleViolationException extends RuntimeException {
  public DomainRuleViolationException(final String message) {
    super(message);
  }
}
