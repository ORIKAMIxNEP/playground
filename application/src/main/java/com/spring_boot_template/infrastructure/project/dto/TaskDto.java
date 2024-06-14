package com.spring_boot_template.infrastructure.project.dto;

import java.util.List;

public record TaskDto (
  TaskId id,
  TaskName name,
  Status status,
  List<AccountId> assignedAccountIds,
  DueDate dueDate,
  PostponeCount postponeCount,
  MaxPostponeCount maxPostponeCount,
  int index
) {}
