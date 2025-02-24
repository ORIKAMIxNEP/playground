package com.playground.presentation.shared.module;

import com.playground.domain.exception.DomainRuleViolationException;
import com.playground.domain.exception.RequestInvalidException;
import com.playground.domain.exception.ResourceConflictException;
import com.playground.domain.exception.ResourceNotFoundException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class GlobalExceptionHandler {
  @ExceptionHandler(value = {RequestInvalidException.class})
  private ResponseEntity<String> handleRequestInvalidException(
      final RequestInvalidException requestInvalidException) {
    final String message = requestInvalidException.getMessage();
    return ResponseEntity.badRequest().body(message);
  }

  @ExceptionHandler(value = {ResourceNotFoundException.class})
  private ResponseEntity<String> handleDomainNotFoundException(
      final ResourceNotFoundException resourceNotFoundException) {
    final String message = resourceNotFoundException.getMessage();
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
  }

  @ExceptionHandler(value = {ResourceConflictException.class})
  private ResponseEntity<String> handleDomainConflictException(
      final ResourceConflictException resourceConflictException) {
    final String message = resourceConflictException.getMessage();
    return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
  }

  @ExceptionHandler(value = {DomainRuleViolationException.class})
  private ResponseEntity<String> handleDomainKnowledgeException(
      final DomainRuleViolationException domainRuleViolationException) {
    final String message = domainRuleViolationException.getMessage();
    return ResponseEntity.unprocessableEntity().body(message);
  }
}
