package com.spring_boot_template.infrastructure.project.dto;

import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.project.task.value.Status;
import com.spring_boot_template.domain.model.project.task.value.TaskId;
import com.spring_boot_template.domain.model.project.task.value.TaskName;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor(force = true)
@Getter
public class TaskDto {
    private final TaskId taskId;
    private final TaskName taskName;
    private final Status status;
    private final ArrayList<AccountId> assignedAccountIds;
    private final int index;
}
