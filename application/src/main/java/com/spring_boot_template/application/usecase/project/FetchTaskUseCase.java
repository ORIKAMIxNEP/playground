package com.spring_boot_template.application.usecase.project;

import com.spring_boot_template.presentation.controller.project.response.FetchTaskResponse;

public interface FetchTaskUseCase {
    FetchTaskResponse execute(final String taskId);
}
