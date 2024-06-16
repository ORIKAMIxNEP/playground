package com.spring_boot_template.infrastructure.project.dto;

import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.project.task.due_date.value.Date;
import com.spring_boot_template.domain.model.project.task.due_date.value.MaxPostponeCount;
import com.spring_boot_template.domain.model.project.task.due_date.value.PostponeCount;
import com.spring_boot_template.domain.model.project.task.value.Status;
import com.spring_boot_template.domain.model.project.task.value.TaskId;
import com.spring_boot_template.domain.model.project.task.value.TaskName;
import java.util.List;

public record TaskDto(
        TaskId id,
        TaskName name,
        Status status,
        List<AccountId> assignedAccountIds,
        Date date,
        PostponeCount postponeCount,
        MaxPostponeCount maxPostponeCount,
        int index) {}
