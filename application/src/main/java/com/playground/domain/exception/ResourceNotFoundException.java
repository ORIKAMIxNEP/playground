package com.playground.domain.exception;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title = "リソース非存在例外")
public final class ResourceNotFoundException extends RuntimeException {
  public ResourceNotFoundException(final String message) {
    super(message);
  }
}
