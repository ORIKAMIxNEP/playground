package com.spring_boot_template.domain.model.task.factory;

import com.spring_boot_template.domain.model.task.TaskEntity;
import com.spring_boot_template.domain.model.task.value.DueDateValue;
import com.spring_boot_template.domain.model.task.value.MaxPostponeCountValue;
import com.spring_boot_template.domain.model.task.value.TaskNameValue;
import com.spring_boot_template.domain.model.user.value.UserIdValue;

public interface TaskFactory {
    TaskEntity createTask(
            final TaskNameValue taskName,
            final UserIdValue userId,
            final DueDateValue dueDate,
            final MaxPostponeCountValue maxPostponeCount);
}
