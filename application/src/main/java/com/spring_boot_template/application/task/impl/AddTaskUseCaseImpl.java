package com.spring_boot_template.application.task.impl;

import com.spring_boot_template.application.task.AddTaskUseCase;
import com.spring_boot_template.domain.model.project.Project;
import com.spring_boot_template.domain.model.project.ProjectRepository;
import com.spring_boot_template.domain.model.project.value.ProjectId;
import com.spring_boot_template.domain.model.task.value.TaskId;
import com.spring_boot_template.domain.model.task.value.TaskName;
import com.spring_boot_template.domain.shared.IdGenerator;
import com.spring_boot_template.presentation.controller.project.request.ProjectIdRequest;
import com.spring_boot_template.presentation.controller.task.request.AddTaskRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class AddTaskUseCaseImpl implements AddTaskUseCase {
    private final ProjectRepository projectRepository;
    private final IdGenerator idGenerator;

    @Override
    @Transactional
    public String execute(
            final ProjectIdRequest projectIdRequest, final AddTaskRequest addTaskRequest) {
        final ProjectId projectId = new ProjectId(projectIdRequest.value());
        final Project project = projectRepository.findProjectByProjectId(projectId);

        final TaskName taskName = new TaskName(addTaskRequest.taskName());
        final TaskId taskId = project.createTask(idGenerator, taskName);
        projectRepository.saveProject(project);
        return taskId.value();
    }
}
