package com.spring_boot_template.application.usecase.project.converter;

import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.project.task.Task;
import com.spring_boot_template.domain.model.project.task.due_date_detail.DueDateDetail;
import com.spring_boot_template.presentation.controller.project.response.DueDateDetailResponse;
import com.spring_boot_template.presentation.controller.project.response.FetchTaskResponse;

public final class FetchTaskResponseConverter {
    public static FetchTaskResponse execute(final Task task) {
        final String taskName = task.getName().getValue();
        final String status = task.getStatus().toString();
        final String[] assignedAccountIds =
                task.getAssignedAccountIds().stream()
                        .map(AccountId::getValue)
                        .toArray(String[]::new);
        final DueDateDetail dueDateDetail = task.getDueDateDetail();
        final String dueDate = dueDateDetail.getDueDate().getValue();
        final int postponeCount = dueDateDetail.getPostponeCount().getValue();
        final int maxPostponeCount = dueDateDetail.getMaxPostponeCount().getValue();

        final DueDateDetailResponse dueDateDetailResponse =
                new DueDateDetailResponse(dueDate, postponeCount, maxPostponeCount);

        return new FetchTaskResponse(taskName, status, assignedAccountIds, dueDateDetailResponse);
    }
}
