package com.playground.application.task;

import com.playground.presentation.controller.project.request.ProjectIdRequest;
import com.playground.presentation.controller.task.request.TaskIdRequest;
import com.playground.presentation.controller.task.request.UpdateTaskRequest;

public interface UpdateTaskUseCase {
  void execute(
      final ProjectIdRequest projectIdRequest,
      final TaskIdRequest taskIdRequest,
      final UpdateTaskRequest updateTaskRequest);
}
