package com.spring_boot_template.application.project;

import com.spring_boot_template.presentation.controller.project.request.AddTaskRequest;

public interface AddTaskUseCase {
    void execute(final AddTaskRequest addTaskRequest);
}
