package com.spring_boot_template.application.project.impl;

import com.spring_boot_template.application.project.FetchTaskUseCase;
import com.spring_boot_template.application.project.converter.FetchTaskResponseConverter;
import com.spring_boot_template.application.project.query.ProjectQueryService;
import com.spring_boot_template.application.project.query.dto.TaskQueryDto;
import com.spring_boot_template.domain.model.project.task.value.TaskId;
import com.spring_boot_template.presentation.controller.project.response.FetchTaskResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class FetchTaskUseCaseImpl implements FetchTaskUseCase {
    private final ProjectQueryService projectQueryService;
    private final FetchTaskResponseConverter fetchTaskResponseConverter;

    @Override
    @Transactional
    public FetchTaskResponse execute(final String taskIdRequest) {
        final TaskId taskId = new TaskId(taskIdRequest);
        final TaskQueryDto taskQueryDto = projectQueryService.findTaskByTaskId(taskId);

        return fetchTaskResponseConverter.execute(taskQueryDto);
    }
}
