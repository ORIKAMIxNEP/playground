package com.spring_boot_template.application.project.task.impl;

import com.spring_boot_template.application.project.task.AdvanceStatusUseCase;
import com.spring_boot_template.application.project.task.converter.AdvanceStatusResponseConverter;
import com.spring_boot_template.domain.model.project.Project;
import com.spring_boot_template.domain.model.project.ProjectRepository;
import com.spring_boot_template.domain.model.project.task.value.Status;
import com.spring_boot_template.domain.model.project.task.value.TaskId;
import com.spring_boot_template.domain.model.project.value.ProjectId;
import com.spring_boot_template.presentation.controller.project.task.response.AdvanceStatusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class AdvanceStatusUseCaseImpl implements AdvanceStatusUseCase {
    private final ProjectRepository projectRepository;
    private final AdvanceStatusResponseConverter advanceStatusResponseConverter;

    @Override
    @Transactional
    public AdvanceStatusResponse execute(
            final String projectIdRequest, final String taskIdRequest) {
        final ProjectId projectId = new ProjectId(projectIdRequest);
        final Project project = projectRepository.findProjectByProjectId(projectId);
        final TaskId taskId = new TaskId(taskIdRequest);
        final Status status = project.advanceTaskStatus(taskId);

        projectRepository.saveProject(project);

        return advanceStatusResponseConverter.execute(status);
    }
}
