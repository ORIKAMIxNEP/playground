package com.spring_boot_template.domain.model.project.task.service;

import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.project.value.ProjectId;

public interface ExistsTaskDomainService {
    void execute(final AccountId accountId, final ProjectId projectId);
}
