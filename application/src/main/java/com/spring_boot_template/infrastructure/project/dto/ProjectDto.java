package com.spring_boot_template.infrastructure.project.dto;

import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.project.value.ProjectName;
import java.util.ArrayList;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(force = true)
@Getter
public final class ProjectDto {
    private final ProjectName projectName;
    private final ArrayList<AccountId> participatingAccountIds;
}
