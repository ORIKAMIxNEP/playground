package com.spring_boot_template.application.project.impl;

import com.spring_boot_template.application.project.FetchProjectsUseCase;
import com.spring_boot_template.application.project.query.ProjectQueryService;
import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.presentation.controller.project.response.FetchProjectsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class FetchProjectsUseCaseImpl implements FetchProjectsUseCase {
    private final ProjectQueryService projectQueryService;

    @Override
    @Transactional
    public FetchProjectsResponse execute(final String participatingAccountIdRequest) {
        final AccountId participatingAccountId = new AccountId(participatingAccountIdRequest);
        return projectQueryService.findProjectsByParticipatingAccountId(participatingAccountId);
    }
}
