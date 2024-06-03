package com.spring_boot_template.application.usecase.task.impl;

import com.spring_boot_template.application.usecase.task.FindTaskByTaskIdUseCase;
import com.spring_boot_template.application.usecase.task.UpdateTaskUseCase;
import com.spring_boot_template.domain.model.task.TaskEntity;
import com.spring_boot_template.domain.model.task.TaskRepository;
import com.spring_boot_template.domain.model.task.value.AssignedUserIdValue;
import com.spring_boot_template.domain.model.task.value.TaskIdValue;
import com.spring_boot_template.domain.model.task.value.TaskNameValue;
import com.spring_boot_template.presentation.controller.task.request.UpdateTaskRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateTaskUseCaseImpl implements UpdateTaskUseCase {
    private final FindTaskByTaskIdUseCase findTaskByTaskIdUseCase;
    private final TaskRepository taskRepository;

    @Override
    @Transactional
    public void execute(final UpdateTaskRequest updateTaskRequest) {
        final String taskId = updateTaskRequest.taskId();
        final TaskIdValue taskIdValue = new TaskIdValue(taskId);

        findTaskByTaskIdUseCase.execute(taskIdValue);

        final Byte column1 = updateTaskRequest.column1();
        final AssignedUserIdValue assignedUserIdValue = new AssignedUserIdValue(column1);
        final String column2 = updateTaskRequest.column2();
        final TaskNameValue taskNameValue = new TaskNameValue(column2);
        final TaskEntity taskEntity =
                new TaskEntity(taskIdValue, assignedUserIdValue, taskNameValue);

        taskRepository.updateTaskName(taskEntity);
    }
}
