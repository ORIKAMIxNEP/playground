package com.spring_boot_template.presentation.validator;

import com.spring_boot_template.domain.exception.AccessDeniedException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class CsrfTokenValidator {
    @Value("${csrf.token}")
    private String configuredCsrfToken;

    public void execute(final String receivedCsrfToken) {
        // CSRFトークンが異なる場合
        if (!configuredCsrfToken.equals(receivedCsrfToken)) {
            throw new AccessDeniedException("Invalid CSRF Token");
        }
    }
}
