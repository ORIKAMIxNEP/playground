package com.spring_boot_template.application.project.task;

import com.spring_boot_template.presentation.controller.project.task.response.FetchTaskResponse;

public interface FetchTaskUseCase {
    FetchTaskResponse execute(final String projectIdRequest, final String taskIdRequest);
}
