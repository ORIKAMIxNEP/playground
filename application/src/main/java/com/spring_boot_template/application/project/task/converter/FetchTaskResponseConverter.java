package com.spring_boot_template.application.project.task.converter;

import com.spring_boot_template.application.project.task.query.dto.TaskQueryDto;
import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.presentation.controller.project.task.due_date_detail.response.FetchDueDateDetailResponse;
import com.spring_boot_template.presentation.controller.project.task.response.FetchTaskResponse;
import org.springframework.stereotype.Service;

@Service
public final class FetchTaskResponseConverter {
    public FetchTaskResponse execute(
            final TaskQueryDto taskQueryDto,
            final FetchDueDateDetailResponse fetchDueDateDetailResponse) {
        final String taskName = taskQueryDto.getTaskName().getValue();
        final String status = taskQueryDto.getStatus().toString();
        final String[] assignedAccountIds =
                taskQueryDto.getAssignedAccountIds().stream()
                        .map(AccountId::getValue)
                        .toArray(String[]::new);

        return new FetchTaskResponse(
                taskName, status, assignedAccountIds, fetchDueDateDetailResponse);
    }
}
