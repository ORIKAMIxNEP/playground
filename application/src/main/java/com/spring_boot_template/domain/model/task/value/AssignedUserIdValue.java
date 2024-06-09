package com.spring_boot_template.domain.model.task.value;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class AssignedUserIdValue {
    @Getter
    @NotNull(message = "TaskId is null")
    @Pattern(
            regexp = "^[0123456789ABCDEFGHJKMNPQRSTVWXYZ]{26}$",
            message = "AssignedUserId is invalid ULID format")
    private final String value;
}
