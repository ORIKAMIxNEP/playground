package com.spring_boot_template.domain.model.account.value;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
public final class AccountId {
    @NotNull(message = "AccountId is null")
    @Pattern(
            regexp = "^[0123456789ABCDEFGHJKMNPQRSTVWXYZ]{26}$",
            message = "AccountId is invalid ULID format")
    private final String value;
}
