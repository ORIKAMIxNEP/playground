package com.spring_boot_template.application.task;

import com.spring_boot_template.presentation.controller.task.response.FetchTaskResponse;

public interface FetchTaskUseCase {
    FetchTaskResponse execute(final String projectIdRequest, final String taskIdRequest);
}
