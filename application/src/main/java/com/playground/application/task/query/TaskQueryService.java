package com.playground.application.task.query;

import com.playground.domain.model.project.value.ProjectId;
import com.playground.domain.model.task.value.TaskId;
import com.playground.presentation.controller.task.response.FetchTaskResponse;

public interface TaskQueryService {
  FetchTaskResponse findTaskByProjectIdAndTaskId(final ProjectId projectId, final TaskId taskId);
}
