package com.spring_boot_template.application.usecase.task;

import com.spring_boot_template.presentation.controller.task.response.FetchTaskByTaskIdResponse;

public interface FetchTaskByTaskIdUseCase {
    FetchTaskByTaskIdResponse execute(final String taskId);
}
