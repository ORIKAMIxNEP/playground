package com.spring_boot_template.domain.model.project.task;

import com.spring_boot_template.domain.exception.DomainException;
import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.project.task.due_date_detail.DueDateDetail;
import com.spring_boot_template.domain.model.project.task.value.Status;
import com.spring_boot_template.domain.model.project.task.value.TaskId;
import com.spring_boot_template.domain.model.project.task.value.TaskName;
import com.spring_boot_template.domain.shared.IdGenerator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.HashSet;
import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode(of = "taskId")
public class Task {
    private final TaskId taskId;
    private TaskName taskName;
    private Status status;
    private final HashSet<AccountId> assignedAccountIds;
    private final DueDateDetail dueDateDetail;

    public static Task createTask(final IdGenerator idGenerator, final TaskName taskName) {
        final TaskId taskId = new TaskId(idGenerator.generateId());
        final Status status = Status.UNDONE;
        final HashSet<AccountId> assignedAccountIds = new HashSet<>();
        final DueDateDetail dueDateDetail = null;

        return new Task(taskId, taskName, status, assignedAccountIds, dueDateDetail);
    }

    public static Task reconstructTask(
            final TaskId taskId,
            final TaskName taskName,
            final Status status,
            final HashSet<AccountId> assignedAccountIds,
            final DueDateDetail dueDateDetail) {
        return new Task(taskId, taskName, status, assignedAccountIds, dueDateDetail);
    }

    public void advanceStatus() {
        status =
                Optional.ofNullable(status.nextStatus())
                        .orElseThrow(() -> new DomainException("Cannot advance Status any more"));
    }

    public void assignAccount(final AccountId assignedAccountId) {
        assignedAccountIds.add(assignedAccountId);
    }
}
