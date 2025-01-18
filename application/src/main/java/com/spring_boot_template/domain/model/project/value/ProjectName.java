package com.spring_boot_template.domain.model.project.value;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProjectName(
        @NotNull(message = "ProjectName is null")
                @Size(
                        min = 1,
                        max = 10,
                        message =
                                "ProjectName must be between {min} and {max} characters in length")
                String value) {}
