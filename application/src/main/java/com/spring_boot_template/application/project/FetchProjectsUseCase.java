package com.spring_boot_template.application.project;

import com.spring_boot_template.presentation.controller.project.response.FetchProjectsResponse;

public interface FetchProjectsUseCase {
    FetchProjectsResponse execute(final String participatingAccountIdRequest);
}
