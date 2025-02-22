package com.playground.infrastructure.task;

import com.playground.application.task.query.FindTaskByProjectIdAndTaskIdDto;
import com.playground.application.task.query.TaskQueryService;
import com.playground.domain.exception.ResourceNotFoundException;
import com.playground.domain.model.project.value.ProjectId;
import com.playground.domain.model.task.Task;
import com.playground.domain.model.task.value.TaskId;
import com.playground.shared.constants.MessageCode;
import com.playground.shared.module.MessageGenerator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
final class TaskQueryServiceImpl implements TaskQueryService {
  private final TaskQueryMapper taskQueryMapper;
  private final MessageGenerator messageGenerator;

  @Override
  public List<FindTaskByProjectIdAndTaskIdDto> findTaskByProjectIdAndTaskId(
      final ProjectId projectId, final TaskId taskId) {
    final List<FindTaskByProjectIdAndTaskIdDto> findTaskByProjectIdAndTaskIdDtos =
        taskQueryMapper.selectTaskByProjectIdAndTaskId(projectId, taskId);
    if (findTaskByProjectIdAndTaskIdDtos.isEmpty()) {
      final String message = messageGenerator.generateMessage(MessageCode.NOT_FOUND, Task.class);
      throw new ResourceNotFoundException(message);
    }
    return findTaskByProjectIdAndTaskIdDtos;
  }
}
