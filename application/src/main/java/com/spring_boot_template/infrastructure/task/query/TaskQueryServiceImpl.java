package com.spring_boot_template.infrastructure.task.query;

import com.spring_boot_template.application.task.query.TaskQueryDto;
import com.spring_boot_template.application.task.query.TaskQueryService;
import com.spring_boot_template.domain.model.project.value.ProjectId;
import com.spring_boot_template.domain.model.task.value.TaskId;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
final class TaskQueryServiceImpl implements TaskQueryService {
    private final TaskQueryMapper taskQueryMapper;

    @Override
    public Optional<TaskQueryDto> findTaskByProjectIdAndTaskId(
            final ProjectId projectId, final TaskId taskId) {
        System.out.println(projectId.getValue());
        System.out.println(taskId.getValue());
        final TaskQueryDto taskQueryDto =
                taskQueryMapper.selectTaskByProjectIdAndTaskId(projectId, taskId);
        System.out.println("aaaaaaaaaaaaaaaaaaaaa");

        return Optional.ofNullable(taskQueryDto);
    }
}
