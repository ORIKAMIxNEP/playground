package com.spring_boot_template.domain.model.project.value;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ProjectName {
    @Getter
    @NotNull(message = "ProjectName is null")
    @Size(
            min = 1,
            max = 10,
            message = "ProjectName must be between {min} and {max} characters in length")
    private final String value;
}
