package com.spring_boot_template.application.project.query.dto;

import com.spring_boot_template.domain.model.project.value.ProjectId;
import com.spring_boot_template.domain.model.project.value.ProjectName;

public record ProjectQueryDto(ProjectId projectId, ProjectName projectName) {}
