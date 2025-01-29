package com.spring_boot_template.application.task;

import com.spring_boot_template.presentation.controller.project.request.ProjectIdRequest;
import com.spring_boot_template.presentation.controller.task.request.AddTaskRequest;

public interface AddTaskUseCase {
    String execute(final ProjectIdRequest projectIdRequest, final AddTaskRequest addTaskRequest);
}
