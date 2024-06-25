package com.spring_boot_template.application.project.task.query;

import com.spring_boot_template.application.project.task.query.dto.TaskQueryDto;
import com.spring_boot_template.domain.model.project.task.value.TaskId;
import com.spring_boot_template.domain.model.project.value.ProjectId;

public interface TaskQueryService {
    TaskQueryDto findTaskByProjectIdAndTaskId(final ProjectId projectId, final TaskId taskId);
}
