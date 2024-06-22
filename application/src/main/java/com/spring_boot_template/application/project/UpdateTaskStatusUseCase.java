package com.spring_boot_template.application.project;

import com.spring_boot_template.presentation.controller.project.request.UpdateTaskStatusRequest;
import com.spring_boot_template.presentation.controller.project.response.UpdateTaskStatusResponse;

public interface UpdateTaskStatusUseCase {
    UpdateTaskStatusResponse execute(final UpdateTaskStatusRequest updateTaskStatusRequest);
}
