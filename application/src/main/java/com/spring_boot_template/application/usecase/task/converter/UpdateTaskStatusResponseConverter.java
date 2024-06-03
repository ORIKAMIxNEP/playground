package com.spring_boot_template.application.usecase.task.converter;

import com.spring_boot_template.domain.model.task.TaskEntity;
import com.spring_boot_template.presentation.controller.task.response.UpdateTaskStatusResponse;

public final class UpdateTaskStatusResponseConverter {
    public static UpdateTaskStatusResponse execute(final TaskEntity task) {
        final String taskStatus = task.getTaskStatus().toString();

        return new UpdateTaskStatusResponse(taskStatus);
    }
}
