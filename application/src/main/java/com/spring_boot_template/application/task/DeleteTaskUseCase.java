package com.spring_boot_template.application.task;

import com.spring_boot_template.presentation.controller.project.request.ProjectIdRequest;
import com.spring_boot_template.presentation.controller.task.request.TaskIdRequest;

public interface DeleteTaskUseCase {
    void execute(final ProjectIdRequest projectIdRequest, final TaskIdRequest taskIdRequest);
}
