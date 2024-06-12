package com.spring_boot_template.application.usecase.project.impl;

import com.spring_boot_template.application.usecase.project.AddTaskUseCase;
import com.spring_boot_template.domain.model.account.Account;
import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.task.Task;
import com.spring_boot_template.domain.model.task.factory.TaskFactory;
import com.spring_boot_template.domain.model.task.value.DueDate;
import com.spring_boot_template.domain.model.task.value.MaxPostponeCount;
import com.spring_boot_template.domain.model.task.value.TaskName;
import com.spring_boot_template.presentation.controller.project.request.AddTaskRequest;
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
        final AccountId userId = new AccountId(addTaskRequest.assignedUserId());
        final Account user = new Account(userId, null, null, null, null);
        // TODO: データベースからUserを取得する

        final TaskName taskName = new TaskName(addTaskRequest.taskName());
        final DueDate dueDate = new DueDate(addTaskRequest.dueDate());
        final MaxPostponeCount maxPostponeCount =
                new MaxPostponeCount(addTaskRequest.maxPostponeCount());
        final Task task = user.createTask(taskFactory, taskName, dueDate, maxPostponeCount);

        taskRepository.add(task);
    }
}
