package com.spring_boot_template.domain.model.user.value;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class PasswordValue {
    @Getter
    @NotNull(message = "Password is null")
    @Size(
            min = 10,
            max = 20,
            message = "Password must be between {min} and {max} characters in length")
    private final String value;
}
