package com.spring_boot_template.domain.model.task.value;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TaskName(
        @NotNull(message = "TaskName is null")
                @Size(
                        min = 1,
                        max = 10,
                        message = "TaskName must be between {min} and {max} characters in length")
                String value) {}
