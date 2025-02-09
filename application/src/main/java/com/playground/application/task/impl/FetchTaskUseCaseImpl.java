package com.playground.application.task.impl;

import com.playground.application.task.FetchTaskUseCase;
import com.playground.application.task.query.TaskQueryService;
import com.playground.domain.model.project.value.ProjectId;
import com.playground.domain.model.task.value.TaskId;
import com.playground.presentation.controller.project.request.ProjectIdRequest;
import com.playground.presentation.controller.task.request.TaskIdRequest;
import com.playground.presentation.controller.task.response.FetchTaskResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FetchTaskUseCaseImpl implements FetchTaskUseCase {
  private final TaskQueryService taskQueryService;

  @Override
  @Transactional
  public FetchTaskResponse execute(
      final ProjectIdRequest projectIdRequest, final TaskIdRequest taskIdRequest) {
    final ProjectId projectId = new ProjectId(projectIdRequest.value());
    final TaskId taskId = new TaskId(taskIdRequest.value());
    return taskQueryService.findTaskByProjectIdAndTaskId(projectId, taskId);
  }
}
