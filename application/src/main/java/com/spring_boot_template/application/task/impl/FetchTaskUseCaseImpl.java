package com.spring_boot_template.application.task.impl;

import com.spring_boot_template.application.due_date_detail.query.DueDateDetailQueryDto;
import com.spring_boot_template.application.due_date_detail.query.DueDateDetailQueryService;
import com.spring_boot_template.application.task.FetchTaskUseCase;
import com.spring_boot_template.application.task.query.TaskQueryDto;
import com.spring_boot_template.application.task.query.TaskQueryService;
import com.spring_boot_template.domain.exception.ResourceNotFoundException;
import com.spring_boot_template.domain.model.project.value.ProjectId;
import com.spring_boot_template.domain.model.task.value.TaskId;
import com.spring_boot_template.presentation.controller.due_date_detail.converter.DueDateDetailResponseConverter;
import com.spring_boot_template.presentation.controller.task.converter.TaskResponseConverter;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class FetchTaskUseCaseImpl implements FetchTaskUseCase {
    private final TaskQueryService taskQueryService;
    private final DueDateDetailQueryService dueDateDetailQueryService;
    private final TaskResponseConverter taskResponseConverter;
    private final DueDateDetailResponseConverter dueDateDetailResponseConverter;

    @Override
    @Transactional
    public TaskQueryDto execute(final String projectIdRequest, final String taskIdRequest) {
        final ProjectId projectId = new ProjectId(projectIdRequest);
        final TaskId taskId = new TaskId(taskIdRequest);

        final Optional<TaskQueryDto> optionalTaskDto =
                taskQueryService.findTaskByProjectIdAndTaskId(projectId, taskId);
        final TaskQueryDto taskQueryDto =
                optionalTaskDto.orElseThrow(
                        () -> new ResourceNotFoundException("Task is not found"));

        final Optional<DueDateDetailQueryDto> optionalDueDateDetailDto =
                dueDateDetailQueryService.findDueDateDetailByTaskId(taskId);
        final DueDateDetailQueryDto dueDateDetailQueryDto = optionalDueDateDetailDto.orElse(null);
        taskQueryDto.setDueDateDetailQueryDto(dueDateDetailQueryDto);

        return taskQueryDto;
    }
}
