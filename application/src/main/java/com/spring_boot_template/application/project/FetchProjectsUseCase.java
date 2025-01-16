package com.spring_boot_template.application.project;

import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.presentation.controller.project.response.FetchProjectsResponse;

public interface FetchProjectsUseCase {
    FetchProjectsResponse execute(final AccountId accountId);
}
