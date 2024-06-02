package com.spring_boot_template.application.usecase.task;

import com.spring_boot_template.presentation.controller.task.request.AddTaskRequest;

public interface AddTaskUseCase {
    void execute(final AddTaskRequest addTaskRequest);
}
