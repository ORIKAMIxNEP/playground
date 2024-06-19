package com.spring_boot_template.infrastructure.project.dto;

import com.spring_boot_template.domain.model.project.value.ProjectName;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(force = true)
@Getter
public class ProjectDto {
    private final ProjectName projectName;
}
