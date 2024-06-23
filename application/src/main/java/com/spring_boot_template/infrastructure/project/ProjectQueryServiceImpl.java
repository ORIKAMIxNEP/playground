package com.spring_boot_template.infrastructure.project;

import com.spring_boot_template.application.project.query.ProjectQueryService;
import com.spring_boot_template.application.project.query.dto.ProjectQueryDto;
import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.project.task.value.TaskId;
import com.spring_boot_template.domain.model.project.value.ProjectId;
import com.spring_boot_template.infrastructure.project.dto.TaskDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
final class ProjectQueryServiceImpl implements ProjectQueryService {
    private final ProjectMapper projectMapper;

    @Override
    public ArrayList<ProjectQueryDto> findProjectsByAccountId(
            final AccountId participatingAccountId) {
            return projectMapper.selectProjectsByAccountId(participatingAccountId).orElse(new ArrayList<>());
    }

    @Override
    public ArrayList<ProjectId> findProjectIdsByAccountId(final AccountId participatingAccountId) {
        return projectMapper.selectProjectIdsByAccountId(participatingAccountId);
    }

    @Override
    public TaskDto findTaskByTaskId(final TaskId taskId){
        return projectMapper.selectTaskByTaskId(taskId);
    }
}
