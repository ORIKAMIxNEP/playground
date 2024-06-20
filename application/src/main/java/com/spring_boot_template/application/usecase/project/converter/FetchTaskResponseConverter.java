package com.spring_boot_template.application.usecase.project.converter;

import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.project.task.Task;
import com.spring_boot_template.presentation.controller.project.response.DueDateDetailResponse;
import com.spring_boot_template.presentation.controller.project.response.FetchTaskResponse;
import java.util.Optional;

public final class FetchTaskResponseConverter {
    public static FetchTaskResponse execute(final Task task) {
        final String taskName = task.getTaskName().getValue();
        final String status = task.getStatus().toString();
        final String[] assignedAccountIds =
                task.getAssignedAccountIds().stream()
                        .map(AccountId::getValue)
                        .toArray(String[]::new);

        final DueDateDetailResponse dueDateDetailResponse =
                Optional.ofNullable(task.getDueDateDetail())
                        .map(
                                dueDateDetail -> {
                                    final String dueDate =
                                            dueDateDetail.getDueDate().getValue().toString();
                                    final int postponeCount =
                                            dueDateDetail.getPostponeCount().getValue();
                                    final int maxPostponeCount =
                                            dueDateDetail.getMaxPostponeCount().getValue();

                                    return new DueDateDetailResponse(
                                            dueDate, postponeCount, maxPostponeCount);
                                })
                        .orElse(null);

        return new FetchTaskResponse(taskName, status, assignedAccountIds, dueDateDetailResponse);
    }
}
