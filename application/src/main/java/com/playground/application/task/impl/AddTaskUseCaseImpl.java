package com.playground.application.task.impl;

import com.playground.application.task.AddTaskUseCase;
import com.playground.domain.model.project.Project;
import com.playground.domain.model.project.ProjectRepository;
import com.playground.domain.model.project.value.ProjectId;
import com.playground.domain.model.task.value.TaskId;
import com.playground.domain.model.task.value.TaskName;
import com.playground.domain.module.IdGenerator;
import com.playground.presentation.controller.project.request.ProjectIdRequest;
import com.playground.presentation.controller.task.request.AddTaskRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class AddTaskUseCaseImpl implements AddTaskUseCase {
  private final ProjectRepository projectRepository;
  private final IdGenerator idGenerator;

  @Override
  @Transactional
  public String execute(
      final ProjectIdRequest projectIdRequest, final AddTaskRequest addTaskRequest) {
    final ProjectId projectId = new ProjectId(projectIdRequest.value());
    final Project project = projectRepository.findProjectByProjectId(projectId);
    final TaskName taskName = new TaskName(addTaskRequest.taskName());
    final TaskId taskId = project.createTask(idGenerator, taskName);
    projectRepository.saveProject(project);
    return taskId.value();
  }
}
