package com.spring_boot_template.application.project.converter;

import com.spring_boot_template.domain.model.project.task.Task;
import com.spring_boot_template.presentation.controller.project.response.UpdateTaskStatusResponse;
import org.springframework.stereotype.Service;

@Service
public final class UpdateTaskStatusResponseConverter {
    public UpdateTaskStatusResponse execute(final Task task) {
        final String taskStatus = task.getStatus().toString();

        return new UpdateTaskStatusResponse(taskStatus);
    }
}
