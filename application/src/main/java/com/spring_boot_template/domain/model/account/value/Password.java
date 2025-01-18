package com.spring_boot_template.domain.model.account.value;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record Password(
        @NotNull(message = "Password is null")
                @Size(
                        min = 10,
                        max = 20,
                        message = "Password must be between {min} and {max} characters in length")
                String value) {}
