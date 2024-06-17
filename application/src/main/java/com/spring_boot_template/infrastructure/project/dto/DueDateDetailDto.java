package com.spring_boot_template.infrastructure.project.dto;

import com.spring_boot_template.domain.model.project.task.due_date_detail.value.DueDate;
import com.spring_boot_template.domain.model.project.task.due_date_detail.value.MaxPostponeCount;
import com.spring_boot_template.domain.model.project.task.due_date_detail.value.PostponeCount;
import com.spring_boot_template.domain.model.project.task.value.TaskId;

public record DueDateDetailDto(
        TaskId taskId,
        DueDate dueDate,
        PostponeCount postponeCount,
        MaxPostponeCount maxPostponeCount) {}
