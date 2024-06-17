package com.spring_boot_template.infrastructure.project.dto;

import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.project.value.ProjectName;
import java.util.HashSet;

public record ProjectDto(ProjectName name, HashSet<AccountId> participatingAccountIds) {}
