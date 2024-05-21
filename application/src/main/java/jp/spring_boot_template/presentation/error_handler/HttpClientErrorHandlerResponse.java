package com.spring_boot_template.presentation.error_handler;

import lombok.Builder;
import org.springframework.http.ResponseEntity;

@Builder
public record HttpClientErrorHandlerResponse(boolean error, ResponseEntity<?> responseEntity) {}
