package com.spring_boot_template.application.project.task.due_date_detail;

import com.spring_boot_template.domain.model.project.task.value.TaskId;
import com.spring_boot_template.presentation.controller.project.task.due_date_detail.response.FetchDueDateDetailResponse;

public interface FetchDueDateDetailUseCase {
    FetchDueDateDetailResponse execute(final TaskId taskId);
}
