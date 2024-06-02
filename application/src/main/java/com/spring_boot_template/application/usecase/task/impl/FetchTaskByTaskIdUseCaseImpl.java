package com.spring_boot_template.application.usecase.task.impl;

import com.spring_boot_template.application.usecase.task.FetchTaskByTaskIdUseCase;
import com.spring_boot_template.domain.exception.LogicValidationException;
import com.spring_boot_template.domain.model.task.TaskEntity;
import com.spring_boot_template.domain.model.task.TaskRepository;
import com.spring_boot_template.domain.model.task.value.AssignedUserIdValue;
import com.spring_boot_template.domain.model.task.value.TaskIdValue;
import com.spring_boot_template.domain.model.task.value.TaskNameValue;
import com.spring_boot_template.presentation.controller.task.response.FetchTaskResponse;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FetchTaskByTaskIdUseCaseImpl implements FetchTaskByTaskIdUseCase {
    private final TaskRepository recordRepository;

    @Override
    @Transactional
    public FetchTaskResponse execute(final String recordId) {
        final TaskIdValue taskIdValue = new TaskIdValue(recordId);

        final TaskEntity taskEntity =
                Optional.ofNullable(recordRepository.fetchTaskByTaskId(taskIdValue))
                        .orElseThrow(() -> new LogicValidationException("Record is not found"));

        final AssignedUserIdValue assignedUserIdValue = taskEntity.getAssignedUserIdValue();
        final byte column1 = assignedUserIdValue.getValue();
        final TaskNameValue taskNameValue = taskEntity.getTaskNameValue();
        final String column2 = taskNameValue.getValue();

        return new FetchTaskResponse(column1, column2);
    }
}
