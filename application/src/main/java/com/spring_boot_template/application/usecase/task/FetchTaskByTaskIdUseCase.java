package com.spring_boot_template.application.usecase.task;

import com.spring_boot_template.presentation.controller.task.response.FetchTaskResponse;

public interface FetchTaskByTaskIdUseCase {
    FetchTaskResponse execute(final String recordId);
}
