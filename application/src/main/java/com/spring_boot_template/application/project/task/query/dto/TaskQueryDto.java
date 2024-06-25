package com.spring_boot_template.application.project.task.query.dto;

import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.project.task.value.Status;
import com.spring_boot_template.domain.model.project.task.value.TaskName;
import java.util.ArrayList;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(force = true)
@Getter
public final class TaskQueryDto {
    private final TaskName taskName;
    private final Status status;
    private final ArrayList<AccountId> assignedAccountIds;
}
