package com.spring_boot_template.application.usecase.task.impl;

import com.spring_boot_template.application.usecase.task.FetchTaskByTaskIdUseCase;
import com.spring_boot_template.application.usecase.task.FindTaskByTaskIdUseCase;
import com.spring_boot_template.application.usecase.task.converter.FetchTaskByTaskIdResponseConverter;
import com.spring_boot_template.domain.model.task.TaskEntity;
import com.spring_boot_template.domain.model.task.value.TaskIdValue;
import com.spring_boot_template.presentation.controller.task.response.FetchTaskByTaskIdResponse;
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
        final TaskIdValue taskIdValue = new TaskIdValue(taskId);
        final TaskEntity task = findTaskByTaskIdUseCase.execute(taskIdValue);

        return FetchTaskByTaskIdResponseConverter.execute(task);
    }
}
