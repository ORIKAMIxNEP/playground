package com.spring_boot_template.domain.model.task.value;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record TaskId(
        @NotNull(message = "TaskId is null")
                @Pattern(
                        regexp = "^[0123456789ABCDEFGHJKMNPQRSTVWXYZ]{26}$",
                        message = "TaskId is invalid ULID format")
                String value) {}
