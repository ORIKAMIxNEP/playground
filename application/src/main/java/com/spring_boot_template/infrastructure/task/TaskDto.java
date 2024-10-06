package com.spring_boot_template.infrastructure.task;

import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.task.value.Status;
import com.spring_boot_template.domain.model.task.value.TaskId;
import com.spring_boot_template.domain.model.task.value.TaskName;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(force = true)
@Getter
public final class TaskDto {
    private final TaskId taskId;
    private final TaskName taskName;
    private final Status status;
    private final Set<AccountId> assignedAccountIds;
}
