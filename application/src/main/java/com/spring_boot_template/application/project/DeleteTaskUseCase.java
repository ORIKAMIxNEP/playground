package com.spring_boot_template.application.project;

import com.spring_boot_template.presentation.controller.project.request.DeleteTaskRequest;

public interface DeleteTaskUseCase {
    void execute(final DeleteTaskRequest deleteTaskRequest);
}
