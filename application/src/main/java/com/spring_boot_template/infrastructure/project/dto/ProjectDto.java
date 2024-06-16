package com.spring_boot_template.infrastructure.project.dto;

import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.project.value.ProjectId;
import com.spring_boot_template.domain.model.project.value.ProjectName;
import java.util.List;

public record ProjectDto(
        ProjectId id,
        ProjectName name,
        List<AccountId> participatingAccountIds,
        List<TaskDto> tasks) {}
