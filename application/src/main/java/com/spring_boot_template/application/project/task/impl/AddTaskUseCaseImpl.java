package com.spring_boot_template.application.project.task.impl;

import com.spring_boot_template.application.project.task.AddTaskUseCase;
import com.spring_boot_template.domain.model.project.Project;
import com.spring_boot_template.domain.model.project.ProjectRepository;
import com.spring_boot_template.domain.model.project.task.value.TaskName;
import com.spring_boot_template.domain.model.project.value.ProjectId;
import com.spring_boot_template.domain.shared.IdGenerator;
import com.spring_boot_template.presentation.controller.project.task.request.AddTaskRequest;
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
    public void execute(final String projectIdRequest, final AddTaskRequest addTaskRequest) {
        final ProjectId projectId = new ProjectId(projectIdRequest);
        final TaskName taskName = new TaskName(addTaskRequest.taskName());
        final Project project = projectRepository.findProjectByProjectId(projectId);

        project.createTask(idGenerator, taskName);
    }
}
