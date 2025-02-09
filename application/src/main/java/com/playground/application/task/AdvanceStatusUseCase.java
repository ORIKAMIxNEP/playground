package com.playground.application.task;

import com.playground.presentation.controller.project.request.ProjectIdRequest;
import com.playground.presentation.controller.task.request.TaskIdRequest;

public interface AdvanceStatusUseCase {
  void execute(final ProjectIdRequest projectIdRequest, final TaskIdRequest taskIdRequest);
}
