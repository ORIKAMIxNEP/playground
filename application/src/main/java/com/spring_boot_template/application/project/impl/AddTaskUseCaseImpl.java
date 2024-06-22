package com.spring_boot_template.application.project.impl;

import com.spring_boot_template.application.project.AddTaskUseCase;
import com.spring_boot_template.presentation.controller.project.request.AddTaskRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AddTaskUseCaseImpl implements AddTaskUseCase {
    @Override
    @Transactional
    public void execute(final AddTaskRequest addTaskRequest) {
        //        // TODO: データベースからUserを取得する
        //        final AccountId userId = new AccountId(addTaskRequest.assignedUserId());
        //        final Account user = new Account(userId, null, null, null, null);
        //        // TODO: データベースからUserを取得する
        //
        //        final TaskName taskName = new TaskName(addTaskRequest.taskName());
        //        final Date date = new Date(addTaskRequest.dueDate());
        //        final MaxPostponeCount maxPostponeCount =
        //                new MaxPostponeCount(addTaskRequest.maxPostponeCount());
        //        final Task task = user.createTask(taskFactory, taskName, date, maxPostponeCount);
        //
        //        taskRepository.add(task);
    }
}
