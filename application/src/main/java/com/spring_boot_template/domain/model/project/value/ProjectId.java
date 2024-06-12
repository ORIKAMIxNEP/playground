package com.spring_boot_template.domain.model.project.value;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
public final class ProjectId {
    @Getter
    @NotNull(message = "ProjectId is null")
    @Pattern(
            regexp = "^[0123456789ABCDEFGHJKMNPQRSTVWXYZ]{26}$",
            message = "ProjectId is invalid ULID format")
    private final String value;
}
