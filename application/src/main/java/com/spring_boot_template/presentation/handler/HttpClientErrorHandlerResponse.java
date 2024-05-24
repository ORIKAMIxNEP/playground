package com.spring_boot_template.presentation.handler;

import lombok.Builder;
import org.springframework.http.ResponseEntity;

@Builder
public record HttpClientErrorHandlerResponse(boolean hasError, ResponseEntity<?> responseEntity) {}
