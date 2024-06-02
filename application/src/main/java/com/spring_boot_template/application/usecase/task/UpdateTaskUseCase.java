package com.spring_boot_template.application.usecase.task;

import com.spring_boot_template.presentation.controller.task.request.UpdateTaskRequest;

public interface UpdateTaskUseCase {
    void execute(final UpdateTaskRequest updateTaskRequest);
}
