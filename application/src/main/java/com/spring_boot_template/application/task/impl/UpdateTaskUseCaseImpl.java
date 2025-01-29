package com.spring_boot_template.application.task.impl;

import com.spring_boot_template.application.task.UpdateTaskUseCase;
import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.project.Project;
import com.spring_boot_template.domain.model.project.ProjectRepository;
import com.spring_boot_template.domain.model.project.value.ProjectId;
import com.spring_boot_template.domain.model.task.value.TaskId;
import com.spring_boot_template.domain.model.task.value.TaskName;
import com.spring_boot_template.presentation.controller.project.request.ProjectIdRequest;
import com.spring_boot_template.presentation.controller.task.request.TaskIdRequest;
import com.spring_boot_template.presentation.controller.task.request.UpdateTaskRequest;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class UpdateTaskUseCaseImpl implements UpdateTaskUseCase {
    private final ProjectRepository projectRepository;

    @Override
    @Transactional
    public void execute(
            final ProjectIdRequest projectIdRequest,
            final TaskIdRequest taskIdRequest,
            final UpdateTaskRequest updateTaskRequest) {
        final ProjectId projectId = new ProjectId(projectIdRequest.value());
        final Project project = projectRepository.findProjectByProjectId(projectId);

        final TaskId taskId = new TaskId(taskIdRequest.value());
        final TaskName taskName = new TaskName(updateTaskRequest.taskName());
        final List<AccountId> assignedAccountIds =
                Arrays.stream(updateTaskRequest.assignedAccountIds()).map(AccountId::new).toList();
        project.updateTask(taskId, taskName, assignedAccountIds);
        projectRepository.saveProject(project);
    }
}
