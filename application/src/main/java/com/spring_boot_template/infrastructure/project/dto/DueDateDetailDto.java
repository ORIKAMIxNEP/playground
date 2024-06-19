package com.spring_boot_template.infrastructure.project.dto;

import com.spring_boot_template.domain.model.project.task.due_date_detail.value.DueDate;
import com.spring_boot_template.domain.model.project.task.due_date_detail.value.MaxPostponeCount;
import com.spring_boot_template.domain.model.project.task.due_date_detail.value.PostponeCount;
import com.spring_boot_template.domain.model.project.task.value.TaskId;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(force = true)
@Getter
public class DueDateDetailDto {
    private final TaskId taskId;
    private final DueDate dueDate;
    private final PostponeCount postponeCount;
    private final MaxPostponeCount maxPostponeCount;
}
