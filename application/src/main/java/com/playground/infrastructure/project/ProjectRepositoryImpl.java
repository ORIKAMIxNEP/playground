package com.playground.infrastructure.project;

import com.playground.domain.exception.ResourceNotFoundException;
import com.playground.domain.model.account.value.AccountId;
import com.playground.domain.model.deadline.Deadline;
import com.playground.domain.model.deadline.value.DueDate;
import com.playground.domain.model.deadline.value.MaxPostponeCount;
import com.playground.domain.model.deadline.value.PostponeCount;
import com.playground.domain.model.project.Project;
import com.playground.domain.model.project.ProjectRepository;
import com.playground.domain.model.project.value.ProjectId;
import com.playground.domain.model.project.value.ProjectName;
import com.playground.domain.model.task.Task;
import com.playground.domain.model.task.value.Status;
import com.playground.domain.model.task.value.TaskId;
import com.playground.domain.model.task.value.TaskName;
import com.playground.shared.constants.MessageCode;
import com.playground.shared.module.MessageGenerator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class ProjectRepositoryImpl implements ProjectRepository {
  private final ProjectMapper projectMapper;
  private final MessageGenerator messageGenerator;

  @Override
  public void saveProject(final Project project) {
    final ProjectId projectId = project.getProjectId();
    final ProjectName projectName = project.getProjectName();
    projectMapper.insertProject(projectId, projectName);
    projectMapper.deleteProjectAccountParticipations(projectId);
    final Set<AccountId> participatingAccountIds = project.getParticipatingAccountIds();
    participatingAccountIds.forEach(
        participatingAccountId ->
            projectMapper.insertProjectAccountParticipation(projectId, participatingAccountId));
    projectMapper.deleteTasks(projectId);
    final List<Task> tasks = new ArrayList<>(project.getTasks());
    saveTasks(projectId, tasks);
  }

  @Override
  public Project findProjectByProjectId(final ProjectId projectId) {
    final ProjectDto projectDto =
        projectMapper
            .selectProjectByProjectId(projectId)
            .orElseThrow(
                () -> {
                  final String message =
                      messageGenerator.generateMessage(MessageCode.NOT_FOUND, Project.class);
                  return new ResourceNotFoundException(message);
                });
    final ProjectName projectName = projectDto.projectName();
    final Set<AccountId> participatingAccountIds =
        new HashSet<>(projectMapper.selectProjectAccountParticipationsByProjectId(projectId));
    final LinkedHashSet<Task> tasks = findTasksByProjectId(projectId);
    return Project.reconstructProject(projectId, projectName, participatingAccountIds, tasks);
  }

  @Override
  public void deleteProject(final ProjectId projectId) {
    projectMapper.deleteProject(projectId);
  }

  private void saveTasks(final ProjectId projectId, final List<Task> tasks) {
    tasks.forEach(
        task -> {
          final TaskId taskId = task.getTaskId();
          final TaskName taskName = task.getTaskName();
          final Status status = task.getStatus();
          final int index = tasks.indexOf(task);
          projectMapper.insertTask(projectId, taskId, taskName, status, index);
          final Set<AccountId> assignedAccountIds = task.getAssignedAccountIds();
          assignedAccountIds.forEach(
              assignedAccountId ->
                  projectMapper.insertTaskAccountAssignment(taskId, assignedAccountId));
          task.getDeadline().ifPresent(deadline -> saveDeadline(taskId, deadline));
        });
  }

  private LinkedHashSet<Task> findTasksByProjectId(final ProjectId projectId) {
    return projectMapper.selectTasksByProjectId(projectId).stream()
        .map(
            taskDto -> {
              final TaskId taskId = taskDto.taskId();
              final TaskName taskName = taskDto.taskName();
              final Status status = taskDto.status();
              final Set<AccountId> assignedAccountIds =
                  new HashSet<>(projectMapper.selectTaskAccountAssignmentsByTaskId(taskId));
              final Deadline deadline = findDeadlineByTaskId(taskId).orElse(null);
              return Task.reconstructTask(taskId, taskName, status, assignedAccountIds, deadline);
            })
        .collect(Collectors.toCollection(LinkedHashSet::new));
  }

  private void saveDeadline(final TaskId taskId, final Deadline deadline) {
    final DueDate dueDate = deadline.getDueDate();
    final PostponeCount postponeCount = deadline.getPostponeCount();
    final MaxPostponeCount maxPostponeCount = deadline.getMaxPostponeCount();
    projectMapper.insertDeadline(taskId, dueDate, postponeCount, maxPostponeCount);
  }

  private Optional<Deadline> findDeadlineByTaskId(final TaskId taskId) {
    return projectMapper
        .selectDeadlineByTaskId(taskId)
        .map(
            deadlineDto -> {
              final DueDate dueDate = new DueDate(deadlineDto.dueDate().toLocalDateTime());
              final PostponeCount postponeCount = deadlineDto.postponeCount();
              final MaxPostponeCount maxPostponeCount = deadlineDto.maxPostponeCount();
              return Deadline.reconstructDeadline(dueDate, postponeCount, maxPostponeCount);
            });
  }
}
