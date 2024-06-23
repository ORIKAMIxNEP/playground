package com.spring_boot_template.application.project.query.dto;

import com.spring_boot_template.domain.model.project.value.ProjectId;
import com.spring_boot_template.domain.model.project.value.ProjectName;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(force = true)
@Getter
public final class ProjectQueryDto {
    private final ProjectId projectId;
    private final ProjectName projectName;
}
