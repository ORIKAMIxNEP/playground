package com.spring_boot_template.infrastructure.project.dto;

import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.project.task.value.Status;
import com.spring_boot_template.domain.model.project.task.value.TaskId;
import com.spring_boot_template.domain.model.project.task.value.TaskName;
import java.util.HashSet;

public record TaskDto(
        TaskId id,
        TaskName name,
        Status status,
        HashSet<AccountId> assignedAccountIds,
        int index) {}
