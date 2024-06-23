package com.spring_boot_template.infrastructure.project.dto;

import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.project.value.ProjectId;
import com.spring_boot_template.domain.model.project.value.ProjectName;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor(force = true)
@Getter
public class ProjectDto {
    private final ProjectId projectId;
    private final ProjectName projectName;
    private final ArrayList<AccountId> participatingAccountIds;
}
