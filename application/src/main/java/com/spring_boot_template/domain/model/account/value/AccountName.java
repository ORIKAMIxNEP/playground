package com.spring_boot_template.domain.model.account.value;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class AccountName {
    @Getter
    @NotNull(message = "AccountName is null")
    @Size(
            min = 1,
            max = 10,
            message = "AccountName must be between {min} and {max} characters in length")
    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    private final String value;
}
