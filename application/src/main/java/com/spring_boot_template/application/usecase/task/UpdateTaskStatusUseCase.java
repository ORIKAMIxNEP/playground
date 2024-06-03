package com.spring_boot_template.application.usecase.task;

import com.spring_boot_template.presentation.controller.task.request.UpdateTaskStatusRequest;
import com.spring_boot_template.presentation.controller.task.response.UpdateTaskStatusResponse;

public interface UpdateTaskStatusUseCase {
    UpdateTaskStatusResponse execute(final UpdateTaskStatusRequest updateTaskStatusRequest);
}
