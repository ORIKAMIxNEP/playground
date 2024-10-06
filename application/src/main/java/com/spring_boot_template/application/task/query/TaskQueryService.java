package com.spring_boot_template.application.task.query;

import com.spring_boot_template.domain.model.project.value.ProjectId;
import com.spring_boot_template.domain.model.task.value.TaskId;
import java.util.Optional;

public interface TaskQueryService {
    Optional<TaskQueryDto> findTaskByProjectIdAndTaskId(
            final ProjectId projectId, final TaskId taskId);
}
