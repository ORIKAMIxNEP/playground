package com.spring_boot_template.application.task.query;

public record FetchTaskQueryDto(
        String taskName,
        String status,
        String assignedAccountId,
        String dueDate,
        int postponeCount,
        int maxPostponeCount) {}
