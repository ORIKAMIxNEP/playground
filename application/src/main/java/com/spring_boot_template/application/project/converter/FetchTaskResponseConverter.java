package com.spring_boot_template.application.project.converter;

import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.project.task.Task;
import com.spring_boot_template.presentation.controller.project.response.DueDateDetailResponse;
import com.spring_boot_template.presentation.controller.project.response.FetchTaskResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public final class FetchTaskResponseConverter {
    public FetchTaskResponse execute(final Task task) {
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
