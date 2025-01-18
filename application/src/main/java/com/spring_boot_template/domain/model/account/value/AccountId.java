package com.spring_boot_template.domain.model.account.value;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.Objects;

public record AccountId(
        @NotNull(message = "AccountId is null")
                @Pattern(
                        regexp = "^[0123456789ABCDEFGHJKMNPQRSTVWXYZ]{26}$",
                        message = "AccountId is invalid ULID format")
                String value) {
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        AccountId accountId = (AccountId) object;
        return Objects.equals(value, accountId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
