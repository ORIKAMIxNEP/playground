package com.spring_boot_template.domain.model.task.value;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class TaskName {
    @Getter
    @NotNull(message = "TaskName is null")
    @Size(
            min = 1,
            max = 10,
            message = "TaskName must be between {min} and {max} characters in length")
    private final String value;
}
