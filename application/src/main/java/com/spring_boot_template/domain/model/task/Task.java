package com.spring_boot_template.domain.model.task;

import com.spring_boot_template.domain.exception.DomainException;
import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.task.value.DueDate;
import com.spring_boot_template.domain.model.task.value.MaxPostponeCount;
import com.spring_boot_template.domain.model.task.value.PostponeCount;
import com.spring_boot_template.domain.model.task.value.Status;
import com.spring_boot_template.domain.model.task.value.TaskId;
import com.spring_boot_template.domain.model.task.value.TaskName;
import com.spring_boot_template.domain.shared.IdGenerator;
import java.util.HashSet;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode(of = "id")
public class Task {
    private final TaskId id;
    private TaskName name;
    private Status status;
    private HashSet<AccountId> assignedAccountIds;
    private DueDate dueDate;
    private PostponeCount postponeCount;
    private MaxPostponeCount maxPostponeCount;

    public static Task create(
            final TaskName name, final DueDate dueDate, final MaxPostponeCount maxPostponeCount) {
        final TaskId id = new TaskId(IdGenerator.generate());
        final Status status = Status.UNDONE;
        final HashSet<AccountId> assignedAccountIds = new HashSet<>();
        final PostponeCount postponeCount = new PostponeCount(0);

        return new Task(
                id, name, status, assignedAccountIds, dueDate, postponeCount, maxPostponeCount);
    }

    public static Task reconstruct(
            final TaskId id,
            final TaskName name,
            final Status status,
            final HashSet<AccountId> assignedAccountIds,
            final DueDate dueDate,
            final PostponeCount postponeCount,
            final MaxPostponeCount maxPostponeCount) {
        return new Task(
                id, name, status, assignedAccountIds, dueDate, postponeCount, maxPostponeCount);
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
