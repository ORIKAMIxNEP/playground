package com.spring_boot_template.infrastructure.task.query;

import com.spring_boot_template.application.due_date_detail.query.DueDateDetailQueryDto;
import com.spring_boot_template.application.due_date_detail.query.DueDateDetailQueryService;
import com.spring_boot_template.application.task.query.TaskQueryDto;
import com.spring_boot_template.application.task.query.TaskQueryService;
import com.spring_boot_template.domain.exception.ResourceNotFoundException;
import com.spring_boot_template.domain.model.project.value.ProjectId;
import com.spring_boot_template.domain.model.task.value.TaskId;
import com.spring_boot_template.presentation.controller.due_date_detail.response.DueDateDetailResponse;
import com.spring_boot_template.presentation.controller.task.response.FetchTaskResponse;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
final class TaskQueryServiceImpl implements TaskQueryService {
    private final TaskQueryMapper taskQueryMapper;
    private final DueDateDetailQueryService dueDateDetailQueryService;

    @Override
    public FetchTaskResponse findTaskByProjectIdAndTaskId(
            final ProjectId projectId, final TaskId taskId) {
        final TaskQueryDto taskQueryDto =
                taskQueryMapper.selectTaskByProjectIdAndTaskId(projectId, taskId);

        if (Objects.isNull(taskQueryDto)) {
            throw new ResourceNotFoundException("Task is not found");
        }

        final Optional<DueDateDetailQueryDto> optionalDueDateDetailDto =
                dueDateDetailQueryService.findDueDateDetailByTaskId(taskId);
        final DueDateDetailQueryDto dueDateDetailQueryDto = optionalDueDateDetailDto.orElse(null);
        taskQueryDto.setDueDateDetailQueryDto(dueDateDetailQueryDto);

        final String taskName = taskQueryDto.getTaskName();
        final String status = taskQueryDto.getStatus();
        final String[] accountIds = taskQueryDto.getAccountIds();
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

        return new FetchTaskResponse(taskName, status, accountIds, dueDateDetailResponse);
    }
}
