package com.spring_boot_template.domain.model.user.value;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class MailAddressValue {
    @Getter
    @NotNull(message = "MailAddress is null")
    @Pattern(
            regexp =
                    "^[a-zA-Z0-9_+-]+(.[a-zA-Z0-9_+-]+)*@([a-zA-Z0-9][a-zA-Z0-9-]*[a-zA-Z0-9]*\\.)+[a-zA-Z]{2,}$",
            message = "MailAddress is invalid e-mail address format")
    private final String value;
}
