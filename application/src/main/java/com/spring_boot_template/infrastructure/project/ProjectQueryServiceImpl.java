package com.spring_boot_template.infrastructure.project;

import com.spring_boot_template.application.project.query.ProjectQueryService;
import com.spring_boot_template.application.project.query.dto.ProjectQueryDto;
import com.spring_boot_template.application.project.query.dto.TaskQueryDto;
import com.spring_boot_template.domain.exception.ValidationException;
import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.project.task.value.TaskId;
import java.util.ArrayList;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
final class ProjectQueryServiceImpl implements ProjectQueryService {
    private final ProjectMapper projectMapper;

    @Override
    public ArrayList<ProjectQueryDto> findProjectsByAccountId(
            final AccountId participatingAccountId) {
        return Optional.ofNullable(projectMapper.selectProjectsByAccountId(participatingAccountId))
                .orElseGet(ArrayList::new);
    }

    @Override
    public TaskQueryDto findTaskByTaskId(final TaskId taskId) {
        final TaskQueryDto taskQueryDto =
                Optional.ofNullable(projectMapper.selectTaskByTaskId(taskId))
                        .orElseThrow(() -> new ValidationException("Task is not found"));

        taskQueryDto.setDueDateDetailQueryDto(projectMapper.selectDueDateDetailByTaskId(taskId));

        return taskQueryDto;
    }
}
