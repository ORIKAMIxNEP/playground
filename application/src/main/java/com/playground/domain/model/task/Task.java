package com.playground.domain.model.task;

import com.playground.domain.exception.DomainRuleViolationException;
import com.playground.domain.exception.ResourceConflictException;
import com.playground.domain.model.account.Account;
import com.playground.domain.model.account.value.AccountId;
import com.playground.domain.model.deadline.Deadline;
import com.playground.domain.model.task.value.Status;
import com.playground.domain.model.task.value.TaskId;
import com.playground.domain.model.task.value.TaskName;
import com.playground.domain.module.IdGenerator;
import com.playground.shared.constants.MessageCode;
import com.playground.shared.module.MessageGenerator;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode(of = "taskId")
public final class Task {
  private final TaskId taskId;
  private TaskName taskName;
  private Status status;
  private final Set<AccountId> assignedAccountIds;
  private final Deadline deadline;

  public Optional<Deadline> getDeadline() {
    return Optional.ofNullable(deadline);
  }

  public static Task createTask(final IdGenerator idGenerator, final TaskName taskName) {
    final TaskId taskId = new TaskId(idGenerator.generateId());
    final Status status = Status.UNDONE;
    final Set<AccountId> assignedAccountIds = Collections.emptySet();
    return new Task(taskId, taskName, status, assignedAccountIds, null);
  }

  public static Task reconstructTask(
      final TaskId taskId,
      final TaskName taskName,
      final Status status,
      final Set<AccountId> assignedAccountIds,
      final Deadline deadline) {
    return new Task(taskId, taskName, status, assignedAccountIds, deadline);
  }

  public void updateTask(
      final TaskName taskName,
      final List<AccountId> assignedAccountIds,
      final MessageGenerator messageGenerator) {
    this.taskName = taskName;
    this.assignedAccountIds.clear();
    assignedAccountIds.forEach(
        assignedAccountId -> {
          final boolean isAssignableToTask = this.assignedAccountIds.add(assignedAccountId);
          if (isAssignableToTask) {
            return;
          }
          final String message =
              messageGenerator.generateMessage(MessageCode.ALREADY_ASSIGNED, Account.class);
          throw new ResourceConflictException(message);
        });
  }

  public void advanceStatus(final MessageGenerator messageGenerator) {
    status =
        status
            .nextStatus()
            .orElseThrow(
                () -> {
                  final String message =
                      messageGenerator.generateMessage(MessageCode.STATUS_CANNOT_ADVANCED, null);
                  return new DomainRuleViolationException(message);
                });
  }

  public void postponeDeadlineDate() {}
}
