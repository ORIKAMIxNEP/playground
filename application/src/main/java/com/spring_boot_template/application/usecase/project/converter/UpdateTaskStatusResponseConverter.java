package com.spring_boot_template.application.usecase.project.converter;

import com.spring_boot_template.domain.model.project.task.Task;
import com.spring_boot_template.presentation.controller.project.response.UpdateTaskStatusResponse;

public final class UpdateTaskStatusResponseConverter {
    public static UpdateTaskStatusResponse execute(final Task task) {
        final String taskStatus = task.getStatus().toString();

        return new UpdateTaskStatusResponse(taskStatus);
    }
}
