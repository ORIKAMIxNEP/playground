package com.spring_boot_template.application.usecase.project;

import com.spring_boot_template.domain.model.task.Task;
import com.spring_boot_template.domain.model.task.value.TaskId;

public interface FindTaskByTaskIdUseCase {
    Task execute(final TaskId taskId);
}
