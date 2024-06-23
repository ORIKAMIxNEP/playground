package com.spring_boot_template.application.project.converter;

import com.spring_boot_template.application.project.query.dto.TaskQueryDto;
import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.presentation.controller.project.response.DueDateDetailResponse;
import com.spring_boot_template.presentation.controller.project.response.FetchTaskResponse;
import org.springframework.stereotype.Service;

@Service
public final class FetchTaskResponseConverter {
    public FetchTaskResponse execute(final TaskQueryDto taskQueryDto) {
        final String taskName = taskQueryDto.getTaskName().getValue();
        final String status = taskQueryDto.getStatus().toString();
        final String[] assignedAccountIds =
                taskQueryDto.getAssignedAccountIds().stream()
                        .map(AccountId::getValue)
                        .toArray(String[]::new);

        final DueDateDetailResponse dueDateDetailResponse =
                taskQueryDto
                        .getDueDateDetailQueryDto()
                        .map(
                                dueDateDetailQueryDto -> {
                                    final String dueDate =
                                            dueDateDetailQueryDto
                                                    .getDueDateQueryDto()
                                                    .getValue()
                                                    .toString();
                                    final int postponeCount =
                                            dueDateDetailQueryDto.getPostponeCount().getValue();
                                    final int maxPostponeCount =
                                            dueDateDetailQueryDto.getMaxPostponeCount().getValue();

                                    return new DueDateDetailResponse(
                                            dueDate, postponeCount, maxPostponeCount);
                                })
                        .orElse(null);

        return new FetchTaskResponse(taskName, status, assignedAccountIds, dueDateDetailResponse);
    }
}
