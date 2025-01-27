package com.spring_boot_template.application.task.impl;

import com.spring_boot_template.application.task.DeleteTaskUseCase;
import com.spring_boot_template.domain.model.project.Project;
import com.spring_boot_template.domain.model.project.ProjectRepository;
import com.spring_boot_template.domain.model.project.value.ProjectId;
import com.spring_boot_template.domain.model.task.value.TaskId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class DeleteTaskUseCaseImpl implements DeleteTaskUseCase {
    private final ProjectRepository projectRepository;

    @Override
    @Transactional
    public void execute(final String projectIdRequest, final String taskIdRequest) {
        final ProjectId projectId = new ProjectId(projectIdRequest);
        final Project project = projectRepository.findProjectByProjectId(projectId);

        final TaskId taskId = new TaskId(taskIdRequest);
        project.deleteTask(taskId);
        projectRepository.saveProject(project);
    }
}
