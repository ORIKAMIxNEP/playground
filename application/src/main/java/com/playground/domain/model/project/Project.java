package com.playground.domain.model.project;

import com.playground.domain.exception.ResourceNotFoundException;
import com.playground.domain.model.account.value.AccountId;
import com.playground.domain.model.project.value.ProjectId;
import com.playground.domain.model.project.value.ProjectName;
import com.playground.domain.model.task.Task;
import com.playground.domain.model.task.value.TaskId;
import com.playground.domain.model.task.value.TaskName;
import com.playground.domain.module.IdGenerator;
import com.playground.shared.constants.MessageCode;
import com.playground.shared.module.MessageGenerator;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public final class Project {
  private final ProjectId projectId;
  private final ProjectName projectName;
  private final Set<AccountId> participatingAccountIds;
  private final LinkedHashSet<Task> tasks;

  public static Project createProject(
      final IdGenerator idGenerator, final ProjectName projectName) {
    final ProjectId projectId = new ProjectId(idGenerator.generateId());
    final Set<AccountId> participatingAccountIds = Collections.emptySet();
    final LinkedHashSet<Task> tasks = new LinkedHashSet<>();
    return new Project(projectId, projectName, participatingAccountIds, tasks);
  }

  public static Project reconstructProject(
      final ProjectId projectId,
      final ProjectName projectName,
      final Set<AccountId> participatingAccountIds,
      final LinkedHashSet<Task> tasks) {
    return new Project(projectId, projectName, participatingAccountIds, tasks);
  }

  public TaskId createTask(final IdGenerator idGenerator, final TaskName taskName) {
    final Task task = Task.createTask(idGenerator, taskName);
    tasks.add(task);
    return task.getTaskId();
  }

  public Task findTaskByTaskId(final TaskId taskId, final MessageGenerator messageGenerator) {
    return tasks.stream()
        .filter(task -> task.getTaskId().equals(taskId))
        .findFirst()
        .orElseThrow(
            () -> {
              final String message =
                  messageGenerator.generateMessage(MessageCode.NOT_FOUND, Task.class);
              return new ResourceNotFoundException(message);
            });
  }

  public void updateTask(
      final TaskId taskId,
      final TaskName taskName,
      final List<AccountId> assignedAccountIds,
      final MessageGenerator messageGenerator) {
    final Task task = findTaskByTaskId(taskId, messageGenerator);
    task.updateTask(taskName, assignedAccountIds, messageGenerator);
  }

  public void advanceTaskStatus(final TaskId taskId, final MessageGenerator messageGenerator) {
    final Task task = findTaskByTaskId(taskId, messageGenerator);
    task.advanceStatus(messageGenerator);
  }

  public void deleteTask(final TaskId taskId, final MessageGenerator messageGenerator) {
    final Task task = findTaskByTaskId(taskId, messageGenerator);
    tasks.remove(task);
  }
}
