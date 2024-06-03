package com.spring_boot_template.application.usecase.task.impl;

import com.spring_boot_template.application.usecase.task.FindTaskByTaskIdUseCase;
import com.spring_boot_template.application.usecase.task.UpdateTaskStatusUseCase;
import com.spring_boot_template.application.usecase.task.converter.UpdateTaskStatusResponseConverter;
import com.spring_boot_template.domain.model.task.TaskEntity;
import com.spring_boot_template.domain.model.task.TaskRepository;
import com.spring_boot_template.domain.model.task.value.TaskIdValue;
import com.spring_boot_template.domain.model.user.UserEntity;
import com.spring_boot_template.presentation.controller.task.request.UpdateTaskStatusRequest;
import com.spring_boot_template.presentation.controller.task.response.UpdateTaskStatusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateTaskStatusUseCaseImpl implements UpdateTaskStatusUseCase {
    private final FindTaskByTaskIdUseCase findTaskByTaskIdUseCase;
    private final TaskRepository taskRepository;

    @Override
    @Transactional
    public UpdateTaskStatusResponse execute(final UpdateTaskStatusRequest updateTaskStatusRequest) {
        final TaskIdValue taskId = new TaskIdValue(updateTaskStatusRequest.taskId());
        final TaskEntity task = findTaskByTaskIdUseCase.execute(taskId);

        // TODO: データベースからUserを取得する
        final UserEntity user = new UserEntity(task.getAssignedUserId(), null, null, null, null);
        // TODO: データベースからUserを取得する

        final TaskEntity updatedTask = user.updateTaskStatus(task);

        taskRepository.updateTaskStatus(updatedTask);

        return UpdateTaskStatusResponseConverter.execute(task);
    }
}
