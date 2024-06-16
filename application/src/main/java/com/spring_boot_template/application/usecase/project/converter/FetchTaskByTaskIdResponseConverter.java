package com.spring_boot_template.application.usecase.project.converter;

import com.spring_boot_template.domain.model.project.task.Task;
import com.spring_boot_template.presentation.controller.project.response.FetchTaskByTaskIdResponse;

public final class FetchTaskByTaskIdResponseConverter {
    public static FetchTaskByTaskIdResponse execute(final Task task) {
        final String taskName = task.getName().getValue();
        final String taskStatus = task.getStatus().toString();
        final String assignedUserId = task.getAssignedUserId().getValue();
        final String dueDate = task.getDate().getValue();
        final int postponeCount = task.getPostponeCount().getValue();
        final int maxPostponeCount = task.getMaxPostponeCount().getValue();

        return new FetchTaskByTaskIdResponse(
                taskName, taskStatus, assignedUserId, dueDate, postponeCount, maxPostponeCount);
    }
}
