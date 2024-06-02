package com.spring_boot_template.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public final class LogicValidationException extends RuntimeException {
    private final String message;
}
