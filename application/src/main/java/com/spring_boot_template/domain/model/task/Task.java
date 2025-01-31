package com.spring_boot_template.domain.model.task;

import com.spring_boot_template.domain.exception.DomainRuleViolationException;
import com.spring_boot_template.domain.exception.ResourceConflictException;
import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.due_date_detail.DueDateDetail;
import com.spring_boot_template.domain.model.task.value.Status;
import com.spring_boot_template.domain.model.task.value.TaskId;
import com.spring_boot_template.domain.model.task.value.TaskName;
import com.spring_boot_template.domain.module.IdGenerator;
import com.spring_boot_template.shared.constants.MessageCode;
import com.spring_boot_template.shared.module.MessageGenerator;
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
    private final DueDateDetail dueDateDetail;

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
            final DueDateDetail dueDateDetail) {
        return new Task(taskId, taskName, status, assignedAccountIds, dueDateDetail);
    }

    public void updateTask(
            final TaskName taskName,
            final List<AccountId> assignedAccountIds,
            final MessageGenerator messageGenerator) {
        this.taskName = taskName;

        this.assignedAccountIds.clear();
        assignedAccountIds.forEach(
                assignedAccountId -> {
                    if (this.assignedAccountIds.add(assignedAccountId)) {
                        return;
                    }
                    final String message =
                            messageGenerator.generateMessage(
                                    MessageCode.ALREADY_ASSIGNED, AccountId.class);
                    throw new ResourceConflictException(message);
                });
    }

    public void advanceStatus(final MessageGenerator messageGenerator) {
        status =
                status.nextStatus()
                        .orElseThrow(
                                () -> {
                                    final String message =
                                            messageGenerator.generateMessage(
                                                    MessageCode.STATUS_CANNOT_ADVANCED, null);
                                    return new DomainRuleViolationException(message);
                                });
    }

    public Optional<DueDateDetail> getDueDateDetail() {
        return Optional.ofNullable(dueDateDetail);
    }
}
