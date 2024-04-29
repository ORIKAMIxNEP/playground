package jp.spring_boot_template.domain.dto.utility;

import org.springframework.http.ResponseEntity;

public record HttpClientErrorHandlerResponse(boolean error, ResponseEntity<?> responseEntity) {}
