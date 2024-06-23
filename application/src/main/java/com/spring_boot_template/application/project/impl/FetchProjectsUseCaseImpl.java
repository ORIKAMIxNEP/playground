package com.spring_boot_template.application.project.impl;

import com.spring_boot_template.application.project.FetchProjectsUseCase;
import com.spring_boot_template.application.project.converter.FetchProjectsResponseConverter;
import com.spring_boot_template.application.project.query.ProjectQueryService;
import com.spring_boot_template.application.project.query.dto.ProjectQueryDto;
import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.presentation.controller.project.response.FetchProjectsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
class FetchProjectsUseCaseImpl implements FetchProjectsUseCase {
    private final ProjectQueryService projectQueryService;
    private final FetchProjectsResponseConverter fetchProjectsResponseConverter;

    @Override
    @Transactional
    public FetchProjectsResponse execute() {
                final AccountId accountId=new AccountId("0123456789ABCDEFGHJKMNPQRS");
                final ArrayList<ProjectQueryDto> projectQueryDtos=projectQueryService.findProjectsByAccountId(accountId);

        return fetchProjectsResponseConverter.execute(projectQueryDtos);
    }
}
