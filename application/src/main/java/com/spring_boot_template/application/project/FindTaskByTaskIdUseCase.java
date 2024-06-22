package com.spring_boot_template.application.project;

import com.spring_boot_template.domain.model.project.task.Task;
import com.spring_boot_template.domain.model.project.task.value.TaskId;

public interface FindTaskByTaskIdUseCase {
    Task execute(final TaskId taskId);
}
