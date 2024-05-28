package com.spring_boot_template.presentation.handler;

import com.spring_boot_template.domain.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public final class ValidationExceptionHandler {
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> execute(final ValidationException exception) {
        final String message = exception.getMessage();
        return ResponseEntity.badRequest().body(message);
    }
}
