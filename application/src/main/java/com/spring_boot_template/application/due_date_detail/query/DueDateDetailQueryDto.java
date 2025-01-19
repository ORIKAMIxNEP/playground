package com.spring_boot_template.application.due_date_detail.query;

public record DueDateDetailQueryDto(String dueDate, int postponeCount, int maxPostponeCount) {}
