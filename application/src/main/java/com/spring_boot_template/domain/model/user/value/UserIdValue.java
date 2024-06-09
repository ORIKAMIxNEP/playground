package com.spring_boot_template.domain.model.user.value;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class UserIdValue {
    @Getter
    @NotNull(message = "UserId is null")
    @Pattern(
            regexp = "^[0123456789ABCDEFGHJKMNPQRSTVWXYZ]{26}$",
            message = "UserId is invalid ULID format")
    private final String value;
}
