package com.spring_boot_template.application.usecase.task;

import com.spring_boot_template.presentation.controller.task.request.DeleteTaskRequest;

public interface DeleteTaskUseCase {
    void execute(final DeleteTaskRequest deleteTaskRequest);
}
