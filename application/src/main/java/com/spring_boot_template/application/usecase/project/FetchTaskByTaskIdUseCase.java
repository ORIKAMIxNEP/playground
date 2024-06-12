package com.spring_boot_template.application.usecase.project;

import com.spring_boot_template.presentation.controller.project.response.FetchTaskByTaskIdResponse;

public interface FetchTaskByTaskIdUseCase {
    FetchTaskByTaskIdResponse execute(final String taskId);
}
