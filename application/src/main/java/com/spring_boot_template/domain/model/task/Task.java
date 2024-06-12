package com.spring_boot_template.domain.model.task;

import com.spring_boot_template.domain.exception.DomainException;
import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.task.value.DueDate;
import com.spring_boot_template.domain.model.task.value.MaxPostponeCount;
import com.spring_boot_template.domain.model.task.value.PostponeCount;
import com.spring_boot_template.domain.model.task.value.Status;
import com.spring_boot_template.domain.model.task.value.TaskId;
import com.spring_boot_template.domain.model.task.value.TaskName;
import java.util.HashSet;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
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

    public void advanceStatus() {
        status =
                Optional.ofNullable(status.nextStatus())
                        .orElseThrow(() -> new DomainException("Cannot advance Status any more"));
    }

    public void assignAccount(final AccountId assignedAccountId) {
        assignedAccountIds.add(assignedAccountId);
    }
}
