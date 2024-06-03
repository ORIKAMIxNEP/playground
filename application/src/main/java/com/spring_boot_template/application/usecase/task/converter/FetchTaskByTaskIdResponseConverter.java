package com.spring_boot_template.application.usecase.task.converter;

import com.spring_boot_template.domain.model.task.TaskEntity;
import com.spring_boot_template.presentation.controller.task.response.FetchTaskByTaskIdResponse;

public final class FetchTaskByTaskIdResponseConverter {
    public static FetchTaskByTaskIdResponse execute(final TaskEntity task) {
        final String taskName = task.getTaskName().getValue();
        final String taskStatus = task.getTaskStatus().toString();
        final String assignedUserId = task.getAssignedUserId().getValue();
        final String dueDate = task.getDueDate().getValue();
        final int postponeCount = task.getPostponeCount().getValue();
        final int maxPostponeCount = task.getMaxPostponeCount().getValue();

        return new FetchTaskByTaskIdResponse(
                taskName, taskStatus, assignedUserId, dueDate, postponeCount, maxPostponeCount);
    }
}
