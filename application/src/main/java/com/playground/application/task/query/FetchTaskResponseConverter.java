package com.playground.application.task.query;

import com.playground.presentation.controller.deadline.response.FetchTaskResponseDeadlineField;
import com.playground.presentation.controller.task.response.FetchTaskResponse;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public final class FetchTaskResponseConverter {
  public FetchTaskResponse convertFetchTaskResponse(
      final List<FindTaskByProjectIdAndTaskIdDto> findTaskByProjectIdAndTaskIdDtos) {
    final FindTaskByProjectIdAndTaskIdDto findTaskByProjectIdAndTaskIdDto =
        findTaskByProjectIdAndTaskIdDtos.get(0);
    final String taskName = findTaskByProjectIdAndTaskIdDto.getTaskName();
    final String status = findTaskByProjectIdAndTaskIdDto.getStatus();
    final String[] assignedAccountIds =
        findTaskByProjectIdAndTaskIdDtos.stream()
            .map(FindTaskByProjectIdAndTaskIdDto::getAssignedAccountId)
            .toArray(String[]::new);
    final FetchTaskResponseDeadlineField fetchTaskResponseDeadlineField =
        findTaskByProjectIdAndTaskIdDto
            .getDueDate()
            .map(
                dueDate -> {
                  final int postponeCount = findTaskByProjectIdAndTaskIdDto.getPostponeCount();
                  final int maxPostponeCount =
                      findTaskByProjectIdAndTaskIdDto.getMaxPostponeCount();
                  return new FetchTaskResponseDeadlineField(
                      dueDate, postponeCount, maxPostponeCount);
                })
            .orElse(null);
    return new FetchTaskResponse(
        taskName, status, assignedAccountIds, fetchTaskResponseDeadlineField);
  }
}
