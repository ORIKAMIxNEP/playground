package jp.spring_boot_template.presentation.error_handler;

import org.springframework.http.ResponseEntity;

public record HttpClientErrorHandlerResponse(boolean error, ResponseEntity<?> responseEntity) {}
