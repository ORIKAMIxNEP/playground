package com.spring_boot_template.infrastructure.project;

import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.project.value.ProjectName;

record ProjectDto(ProjectName projectName, AccountId accountId) {}
