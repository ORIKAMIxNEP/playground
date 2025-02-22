package com.playground.infrastructure.task;

import com.playground.application.task.query.FindTaskByProjectIdAndTaskIdDto;
import com.playground.domain.model.project.value.ProjectId;
import com.playground.domain.model.task.value.TaskId;
import java.util.List;

interface TaskQueryMapper {
  List<FindTaskByProjectIdAndTaskIdDto> selectTaskByProjectIdAndTaskId(
      final ProjectId projectId, TaskId taskId);
}
