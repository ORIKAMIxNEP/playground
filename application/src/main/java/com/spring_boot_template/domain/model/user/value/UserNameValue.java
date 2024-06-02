package com.spring_boot_template.domain.model.user.value;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserNameValue {
    @Getter
    @NotNull(message = "UserName is null")
    @Size(
            min = 1,
            max = 10,
            message = "UserName must be between {min} and {max} characters in length")
    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    private final String value;
}
