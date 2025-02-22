package com.playground.application.task.impl;

import com.playground.application.task.AdvanceStatusUseCase;
import com.playground.domain.model.project.Project;
import com.playground.domain.model.project.ProjectRepository;
import com.playground.domain.model.project.value.ProjectId;
import com.playground.domain.model.task.value.TaskId;
import com.playground.presentation.controller.project.request.ProjectIdRequest;
import com.playground.presentation.controller.task.request.TaskIdRequest;
import com.playground.shared.module.MessageGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class AdvanceStatusUseCaseImpl implements AdvanceStatusUseCase {
  private final ProjectRepository projectRepository;
  private final MessageGenerator messageGenerator;

  @Override
  @Transactional
  public void execute(final ProjectIdRequest projectIdRequest, final TaskIdRequest taskIdRequest) {
    final ProjectId projectId = new ProjectId(projectIdRequest.value());
    final Project project = projectRepository.findProjectByProjectId(projectId);
    final TaskId taskId = new TaskId(taskIdRequest.value());
    project.advanceTaskStatus(taskId, messageGenerator);
    projectRepository.saveProject(project);
  }
}
