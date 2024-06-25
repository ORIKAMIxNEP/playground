package com.spring_boot_template.infrastructure.project.task.dto;

import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.project.task.value.Status;
import com.spring_boot_template.domain.model.project.task.value.TaskId;
import com.spring_boot_template.domain.model.project.task.value.TaskName;
import java.util.ArrayList;
import java.util.Optional;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(force = true)
@Getter
public final class TaskDto {
    private final TaskId taskId;
    private final TaskName taskName;
    private final Status status;
    private final ArrayList<AccountId> assignedAccountIds;

    public Optional<ArrayList<AccountId>> getAssignedAccountIds() {
        return Optional.ofNullable(assignedAccountIds);
    }
}
