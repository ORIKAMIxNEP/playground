package com.spring_boot_template.application.project;

import com.spring_boot_template.presentation.controller.project.response.FetchTaskResponse;

public interface FetchTaskUseCase {
    FetchTaskResponse execute(final String taskIdRequest);
}
