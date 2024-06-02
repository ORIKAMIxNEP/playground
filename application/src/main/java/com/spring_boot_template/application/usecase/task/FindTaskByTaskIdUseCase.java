package com.spring_boot_template.application.usecase.task;

import com.spring_boot_template.domain.model.task.TaskEntity;
import com.spring_boot_template.domain.model.task.value.TaskIdValue;

public interface FindTaskByTaskIdUseCase {
    TaskEntity execute(final TaskIdValue taskId);
}
