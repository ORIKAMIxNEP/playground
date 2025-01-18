package com.spring_boot_template.domain.model.task;

import com.spring_boot_template.domain.exception.DomainKnowledgeException;
import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.due_date_detail.DueDateDetail;
import com.spring_boot_template.domain.model.task.value.Status;
import com.spring_boot_template.domain.model.task.value.TaskId;
import com.spring_boot_template.domain.model.task.value.TaskName;
import com.spring_boot_template.domain.shared.IdGenerator;
import java.util.Collections;
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
    private final Set<AccountId> accountIds;
    private final DueDateDetail dueDateDetail;

    public static Task createTask(final IdGenerator idGenerator, final TaskName taskName) {
        final TaskId taskId = new TaskId(idGenerator.generateId());
        final Status status = Status.UNDONE;
        final Set<AccountId> accountIds = Collections.emptySet();
        final DueDateDetail dueDateDetail = null;

        return new Task(taskId, taskName, status, accountIds, dueDateDetail);
    }

    public static Task reconstructTask(
            final TaskId taskId,
            final TaskName taskName,
            final Status status,
            final Set<AccountId> accountIds,
            final DueDateDetail dueDateDetail) {
        return new Task(taskId, taskName, status, accountIds, dueDateDetail);
    }

    public Optional<DueDateDetail> getDueDateDetail() {
        return Optional.ofNullable(dueDateDetail);
    }

    public void advanceStatus() {
        status =
                status.nextStatus()
                        .orElseThrow(
                                () ->
                                        new DomainKnowledgeException(
                                                "Cannot advance Status any more"));
    }

    public void assignAccount(final AccountId accountId) {
        accountIds.add(accountId);
    }
}
