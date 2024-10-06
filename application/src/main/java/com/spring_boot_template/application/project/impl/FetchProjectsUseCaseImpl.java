package com.spring_boot_template.application.project.impl;

import com.spring_boot_template.application.project.FetchProjectsUseCase;
import com.spring_boot_template.application.project.query.ProjectQueryDto;
import com.spring_boot_template.application.project.query.ProjectQueryService;
import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.presentation.controller.project.converter.ProjectResponsesConverter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class FetchProjectsUseCaseImpl implements FetchProjectsUseCase {
    private final ProjectQueryService projectQueryService;
    private final ProjectResponsesConverter projectResponsesConverter;

    @Override
    @Transactional
    public List<ProjectQueryDto> execute() {
        final AccountId accountId = new AccountId("0123456789ABCDEFGHJKMNPQRS");

        return projectQueryService.findProjectsByParticipatingAccountId(accountId);
    }
}
