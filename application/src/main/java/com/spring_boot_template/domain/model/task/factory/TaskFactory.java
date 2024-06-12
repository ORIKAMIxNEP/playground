package com.spring_boot_template.domain.model.task.factory;

import com.spring_boot_template.domain.model.task.Task;
import com.spring_boot_template.domain.model.task.value.DueDate;
import com.spring_boot_template.domain.model.task.value.MaxPostponeCount;
import com.spring_boot_template.domain.model.task.value.TaskName;

public interface TaskFactory {
    Task create(
            final TaskName name, final DueDate dueDate, final MaxPostponeCount maxPostponeCount);
}
