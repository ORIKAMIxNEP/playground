package com.spring_boot_template.presentation.handler;

import com.spring_boot_template.domain.exception.DomainConflictException;
import com.spring_boot_template.domain.exception.DomainKnowledgeException;
import com.spring_boot_template.domain.exception.DomainNotFoundException;
import com.spring_boot_template.domain.exception.RequestInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
final class GlobalExceptionHandler {
    @ExceptionHandler(value = {RequestInvalidException.class})
    private ResponseEntity<String> handleRequestInvalidException(
            final RequestInvalidException requestInvalidException) {
        final String message = requestInvalidException.getMessage();
        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler(value = {DomainNotFoundException.class})
    private ResponseEntity<String> handleDomainNotFoundException(
            final DomainNotFoundException domainNotFoundException) {
        final String message = domainNotFoundException.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(value = {DomainConflictException.class})
    private ResponseEntity<String> handleDomainConflictException(
            final DomainConflictException domainConflictException) {
        final String message = domainConflictException.getMessage();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }

    @ExceptionHandler(value = {DomainKnowledgeException.class})
    private ResponseEntity<String> handleDomainKnowledgeException(
            final DomainKnowledgeException domainKnowledgeException) {
        final String message = domainKnowledgeException.getMessage();
        return ResponseEntity.unprocessableEntity().body(message);
    }
}
