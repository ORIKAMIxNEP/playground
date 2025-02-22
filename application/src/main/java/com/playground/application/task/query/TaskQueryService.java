package com.playground.application.task.query;

import com.playground.domain.model.project.value.ProjectId;
import com.playground.domain.model.task.value.TaskId;
import java.util.List;

public interface TaskQueryService {
  List<FindTaskByProjectIdAndTaskIdDto> findTaskByProjectIdAndTaskId(
      final ProjectId projectId, final TaskId taskId);
}
