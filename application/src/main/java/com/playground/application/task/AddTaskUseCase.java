package com.playground.application.task;

import com.playground.presentation.controller.project.request.ProjectIdRequest;
import com.playground.presentation.controller.task.request.AddTaskRequest;

public interface AddTaskUseCase {
  String execute(final ProjectIdRequest projectIdRequest, final AddTaskRequest addTaskRequest);
}
