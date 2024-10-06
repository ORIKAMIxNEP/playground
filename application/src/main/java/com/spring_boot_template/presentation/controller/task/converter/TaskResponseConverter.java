package com.spring_boot_template.presentation.controller.task.converter;

import com.spring_boot_template.application.due_date_detail.query.DueDateDetailQueryDto;
import com.spring_boot_template.application.task.query.TaskQueryDto;
import com.spring_boot_template.presentation.controller.due_date_detail.response.DueDateDetailResponse;
import com.spring_boot_template.presentation.controller.task.response.TaskResponse;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public final class TaskResponseConverter {
    public TaskResponse execute(final TaskQueryDto taskQueryDto) {
        final String taskName = taskQueryDto.getTaskName();
        final String status = taskQueryDto.getStatus();
        final String[] assignedAccountIds = taskQueryDto.getAssignedAccountIds();
        final Optional<DueDateDetailQueryDto> optionalDueDateDetailDto =
                taskQueryDto.getDueDateDetailQueryDto();

        final DueDateDetailResponse dueDateDetailResponse =
                optionalDueDateDetailDto
                        .map(
                                dueDateDetailDto -> {
                                    final String dueDate =
                                            dueDateDetailDto
                                                    .getDueDate()
                                                    .toString(); // .toLocalDateTime().toString();
                                    final int postponeCount = dueDateDetailDto.getPostponeCount();
                                    final int maxPostponeCount =
                                            dueDateDetailDto.getMaxPostponeCount();

                                    return new DueDateDetailResponse(
                                            dueDate, postponeCount, maxPostponeCount);
                                })
                        .orElse(null);

        return new TaskResponse(taskName, status, assignedAccountIds, dueDateDetailResponse);
    }
}
