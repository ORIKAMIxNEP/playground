package com.spring_boot_template.presentation.handler;

import com.spring_boot_template.domain.exception.LogicValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public final class LogicValidationExceptionHandler {
    @ExceptionHandler(LogicValidationException.class)
    public ResponseEntity<String> handle(final LogicValidationException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
