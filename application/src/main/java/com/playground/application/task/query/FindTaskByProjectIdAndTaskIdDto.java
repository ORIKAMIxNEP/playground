package com.playground.application.task.query;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@Getter
public final class FindTaskByProjectIdAndTaskIdDto {
  private final String taskName;
  private final String status;
  private final String assignedAccountId;
  private final String dueDate;
  private final int postponeCount;
  private final int maxPostponeCount;

  public Optional<String> getDueDate() {
    return Optional.ofNullable(dueDate);
  }
}
