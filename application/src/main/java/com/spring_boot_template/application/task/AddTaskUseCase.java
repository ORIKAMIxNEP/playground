package com.spring_boot_template.application.task;

import com.spring_boot_template.presentation.controller.task.request.TaskRequest;

public interface AddTaskUseCase {
    void execute(final String projectIdRequest, final TaskRequest taskRequest);
}
