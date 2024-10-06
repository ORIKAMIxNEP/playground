package com.spring_boot_template.application.project.query;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(force = true)
@Getter
public final class ProjectQueryDto {
    private final String projectId;
    private final String projectName;
}
