package com.spring_boot_template.domain.model.task;

import com.spring_boot_template.domain.model.task.value.TaskIdValue;
import com.spring_boot_template.domain.model.user.value.UserIdValue;

public interface TaskRepository {
    void addTask(final TaskEntity task);

    TaskEntity fetchTaskByTaskId(final TaskIdValue taskId);

    int countTaskByUserId(final UserIdValue userId);

    void updateTaskName(final TaskEntity task);

    void deleteRecord(final TaskIdValue taskId);
}
