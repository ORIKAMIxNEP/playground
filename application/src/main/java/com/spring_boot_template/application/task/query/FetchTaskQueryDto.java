package com.spring_boot_template.application.task.query;

public record FetchTaskQueryDto(
        String taskName,
        String status,
        String accountId,
        String dueDate,
        int postponeCount,
        int maxPostponeCount) {}
