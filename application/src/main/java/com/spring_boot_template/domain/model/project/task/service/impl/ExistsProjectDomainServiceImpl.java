package com.spring_boot_template.domain.model.project.task.service.impl;

import com.spring_boot_template.application.project.query.ProjectQueryService;
import com.spring_boot_template.domain.exception.ValidationException;
import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.project.service.ExistsProjectDomainService;
import com.spring_boot_template.domain.model.project.value.ProjectId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
final class ExistsProjectDomainServiceImpl implements ExistsProjectDomainService {
    private final ProjectQueryService projectQueryService;

    @Override
    public void execute(final AccountId accountId, final ProjectId projectId) {
        projectQueryService.findProjectIdsByAccountId(accountId).stream()
                .filter(projectId::equals)
                .findFirst()
                .orElseThrow(() -> new ValidationException("Project is not found"));
    }
}
