package com.spring_boot_template.application.project.task;

import com.spring_boot_template.presentation.controller.project.task.request.AddTaskRequest;

public interface AddTaskUseCase {
    void execute(final String projectIdRequest, final AddTaskRequest addTaskRequest);
}
