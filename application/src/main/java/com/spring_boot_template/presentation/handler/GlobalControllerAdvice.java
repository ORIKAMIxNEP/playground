package com.spring_boot_template.presentation.handler;

import com.spring_boot_template.domain.exception.DomainException;
import com.spring_boot_template.domain.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
final class GlobalControllerAdvice {
    @ExceptionHandler(
            value = {
                ValidationException.class,
                MethodArgumentNotValidException.class,
                DomainException.class
            })
    private ResponseEntity<String> execute(final Exception exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
