package com.playground.application.task.impl;

import com.playground.application.task.UpdateTaskUseCase;
import com.playground.domain.model.account.value.AccountId;
import com.playground.domain.model.project.Project;
import com.playground.domain.model.project.ProjectRepository;
import com.playground.domain.model.project.value.ProjectId;
import com.playground.domain.model.task.value.TaskId;
import com.playground.domain.model.task.value.TaskName;
import com.playground.presentation.controller.project.request.ProjectIdRequest;
import com.playground.presentation.controller.task.request.TaskIdRequest;
import com.playground.presentation.controller.task.request.UpdateTaskRequest;
import com.playground.shared.module.MessageGenerator;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateTaskUseCaseImpl implements UpdateTaskUseCase {
  private final ProjectRepository projectRepository;
  private final MessageGenerator messageGenerator;

  @Override
  @Transactional
  public void execute(
      final ProjectIdRequest projectIdRequest,
      final TaskIdRequest taskIdRequest,
      final UpdateTaskRequest updateTaskRequest) {
    final ProjectId projectId = new ProjectId(projectIdRequest.value());
    final Project project = projectRepository.findProjectByProjectId(projectId);

    final TaskId taskId = new TaskId(taskIdRequest.value());
    final TaskName taskName = new TaskName(updateTaskRequest.taskName());
    final List<AccountId> assignedAccountIds =
        Arrays.stream(updateTaskRequest.assignedAccountIds()).map(AccountId::new).toList();
    project.updateTask(taskId, taskName, assignedAccountIds, messageGenerator);
    projectRepository.saveProject(project);
  }
}
