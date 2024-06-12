package com.spring_boot_template.application.usecase.project.impl;

import com.spring_boot_template.application.usecase.project.FetchTaskByTaskIdUseCase;
import com.spring_boot_template.application.usecase.project.FindTaskByTaskIdUseCase;
import com.spring_boot_template.application.usecase.project.converter.FetchTaskByTaskIdResponseConverter;
import com.spring_boot_template.domain.model.task.Task;
import com.spring_boot_template.domain.model.task.value.TaskId;
import com.spring_boot_template.presentation.controller.project.response.FetchTaskByTaskIdResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class FetchTaskByTaskIdUseCaseImpl implements FetchTaskByTaskIdUseCase {
    private final FindTaskByTaskIdUseCase findTaskByTaskIdUseCase;

    @Override
    @Transactional
    public FetchTaskByTaskIdResponse execute(final String taskId) {
        final TaskId taskIdValue = new TaskId(taskId);
        final Task task = findTaskByTaskIdUseCase.execute(taskIdValue);

        return FetchTaskByTaskIdResponseConverter.execute(task);
    }
}
