package com.spring_boot_template.application.usecase.task.impl;

import com.spring_boot_template.application.usecase.task.AddTaskUseCase;
import com.spring_boot_template.domain.model.task.TaskEntity;
import com.spring_boot_template.domain.model.task.TaskRepository;
import com.spring_boot_template.domain.model.task.factory.TaskFactory;
import com.spring_boot_template.domain.model.task.value.DueDateValue;
import com.spring_boot_template.domain.model.task.value.MaxPostponeCountValue;
import com.spring_boot_template.domain.model.task.value.TaskNameValue;
import com.spring_boot_template.domain.model.user.UserEntity;
import com.spring_boot_template.domain.model.user.value.UserIdValue;
import com.spring_boot_template.presentation.controller.task.request.AddTaskRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AddTaskUseCaseImpl implements AddTaskUseCase {
    private final TaskFactory taskFactory;
    private final TaskRepository taskRepository;

    @Override
    @Transactional
    public void execute(final AddTaskRequest addTaskRequest) {
        // TODO: データベースからUserを取得する
        final UserIdValue userId = new UserIdValue(addTaskRequest.assignedUserId());
        final UserEntity user = new UserEntity(userId, null, null, null, null);
        // TODO: データベースからUserを取得する

        final TaskNameValue taskName = new TaskNameValue(addTaskRequest.taskName());
        final DueDateValue dueDate = new DueDateValue(addTaskRequest.dueDate());
        final MaxPostponeCountValue maxPostponeCount =
                new MaxPostponeCountValue(addTaskRequest.maxPostponeCount());
        final TaskEntity task = user.createTask(taskFactory, taskName, dueDate, maxPostponeCount);

        taskRepository.addTask(task);
    }
}
