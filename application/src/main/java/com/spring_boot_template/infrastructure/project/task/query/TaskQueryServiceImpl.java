package com.spring_boot_template.infrastructure.project.task.query;

import com.spring_boot_template.application.project.task.query.TaskQueryService;
import com.spring_boot_template.application.project.task.query.dto.TaskQueryDto;
import com.spring_boot_template.domain.exception.ValidationException;
import com.spring_boot_template.domain.model.project.task.value.TaskId;
import com.spring_boot_template.domain.model.project.value.ProjectId;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
final class TaskQueryServiceImpl implements TaskQueryService {
    private final TaskQueryMapper taskQueryMapper;

    @Override
    public TaskQueryDto findTaskByProjectIdAndTaskId(
            final ProjectId projectId, final TaskId taskId) {
        final Optional<TaskQueryDto> optionalTaskQueryDto =
                Optional.ofNullable(
                        taskQueryMapper.selectTaskByProjectIdAndTaskId(projectId, taskId));

        return optionalTaskQueryDto.orElseThrow(() -> new ValidationException("Task is not found"));
    }
}
