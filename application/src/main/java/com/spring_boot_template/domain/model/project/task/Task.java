package com.spring_boot_template.domain.model.project.task;

import com.spring_boot_template.domain.exception.DomainException;
import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.project.task.due_date_detail.DueDateDetail;
import com.spring_boot_template.domain.model.project.task.value.Status;
import com.spring_boot_template.domain.model.project.task.value.TaskId;
import com.spring_boot_template.domain.model.project.task.value.TaskName;
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
    private final HashSet<AccountId> assignedAccountIds;
    private final DueDateDetail dueDateDetail;

    public static Task create(final TaskName name) {
        final TaskId id = new TaskId(IdGenerator.generate());
        final Status status = Status.UNDONE;
        final HashSet<AccountId> assignedAccountIds = new HashSet<>();
        final DueDateDetail dueDateDetail = null;

        return new Task(id, name, status, assignedAccountIds, dueDateDetail);
    }

    public static Task reconstruct(
            final TaskId id,
            final TaskName name,
            final Status status,
            final HashSet<AccountId> assignedAccountIds,
            final DueDateDetail dueDateDetail) {
        return new Task(id, name, status, assignedAccountIds, dueDateDetail);
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
