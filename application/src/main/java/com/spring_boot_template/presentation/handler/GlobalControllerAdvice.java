package com.spring_boot_template.presentation.handler;

import com.spring_boot_template.domain.exception.DomainKnowledgeException;
import com.spring_boot_template.domain.exception.ResourceConflictException;
import com.spring_boot_template.domain.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
final class GlobalControllerAdvice {
    @ExceptionHandler(
            value = {
                MethodArgumentNotValidException.class,
            })
    private ResponseEntity<String> handleMethodArgumentNotValidException(
            final Exception exception) {
        final String message = exception.getMessage();
        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    private ResponseEntity<String> handleResourceNotFoundException(final Exception exception) {
        final String message = exception.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(value = {ResourceConflictException.class})
    private ResponseEntity<String> handleResourceConflictException(final Exception exception) {
        final String message = exception.getMessage();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }

    @ExceptionHandler(value = {DomainKnowledgeException.class})
    private ResponseEntity<String> handleDomainKnowledgeException(final Exception exception) {
        final String message = exception.getMessage();
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(message);
    }
}
