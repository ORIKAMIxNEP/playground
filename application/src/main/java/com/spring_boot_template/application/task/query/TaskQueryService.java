package com.spring_boot_template.application.task.query;

import com.spring_boot_template.domain.model.project.value.ProjectId;
import com.spring_boot_template.domain.model.task.value.TaskId;
import com.spring_boot_template.presentation.controller.task.response.FetchTaskResponse;

public interface TaskQueryService {
    FetchTaskResponse findTaskByProjectIdAndTaskId(final ProjectId projectId, final TaskId taskId);
}
