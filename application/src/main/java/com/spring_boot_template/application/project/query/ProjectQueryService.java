package com.spring_boot_template.application.project.query;

import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.presentation.controller.project.response.FetchProjectsResponse;

public interface ProjectQueryService {
    FetchProjectsResponse findProjectsByAccountId(final AccountId accountId);
}
