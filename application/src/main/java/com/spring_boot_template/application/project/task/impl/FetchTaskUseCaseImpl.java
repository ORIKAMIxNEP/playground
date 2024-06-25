package com.spring_boot_template.application.project.task.impl;

import com.spring_boot_template.application.project.task.FetchTaskUseCase;
import com.spring_boot_template.application.project.task.converter.FetchTaskResponseConverter;
import com.spring_boot_template.application.project.task.due_date_detail.FetchDueDateDetailUseCase;
import com.spring_boot_template.application.project.task.query.TaskQueryService;
import com.spring_boot_template.application.project.task.query.dto.TaskQueryDto;
import com.spring_boot_template.domain.model.project.task.value.TaskId;
import com.spring_boot_template.domain.model.project.value.ProjectId;
import com.spring_boot_template.presentation.controller.project.task.due_date_detail.response.FetchDueDateDetailResponse;
import com.spring_boot_template.presentation.controller.project.task.response.FetchTaskResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class FetchTaskUseCaseImpl implements FetchTaskUseCase {
    private final TaskQueryService taskQueryService;
    private final FetchDueDateDetailUseCase fetchDueDateDetailUseCase;
    private final FetchTaskResponseConverter fetchTaskResponseConverter;

    @Override
    @Transactional
    public FetchTaskResponse execute(final String projectIdRequest, final String taskIdRequest) {
        final ProjectId projectId = new ProjectId(projectIdRequest);
        final TaskId taskId = new TaskId(taskIdRequest);
        final TaskQueryDto taskQueryDto =
                taskQueryService.findTaskByProjectIdAndTaskId(projectId, taskId);
        final FetchDueDateDetailResponse fetchDueDateDetailResponse =
                fetchDueDateDetailUseCase.execute(taskId);

        return fetchTaskResponseConverter.execute(taskQueryDto, fetchDueDateDetailResponse);
    }
}
