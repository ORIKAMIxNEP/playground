package com.spring_boot_template.infrastructure.task;

import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.task.value.Status;
import com.spring_boot_template.domain.model.task.value.TaskId;
import com.spring_boot_template.domain.model.task.value.TaskName;

public record TaskDto(TaskId taskId, TaskName taskName, Status status, AccountId accountId) {}
