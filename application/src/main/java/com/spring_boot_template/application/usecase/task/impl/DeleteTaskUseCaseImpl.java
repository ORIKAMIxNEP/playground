package com.spring_boot_template.application.usecase.task.impl;

import com.spring_boot_template.application.usecase.task.DeleteTaskUseCase;
import com.spring_boot_template.application.usecase.task.FindTaskByTaskIdUseCase;
import com.spring_boot_template.domain.model.task.TaskRepository;
import com.spring_boot_template.domain.model.task.value.TaskIdValue;
import com.spring_boot_template.presentation.controller.task.request.DeleteTaskRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteTaskUseCaseImpl implements DeleteTaskUseCase {
    private final FindTaskByTaskIdUseCase findTaskByTaskIdUseCase;
    private final TaskRepository taskRepository;

    @Transactional
    public void execute(final DeleteTaskRequest deleteTaskRequest) {
        final TaskIdValue taskId = new TaskIdValue(deleteTaskRequest.taskId());

        findTaskByTaskIdUseCase.execute(taskId);
        taskRepository.deleteTask(taskId);
    }
}
