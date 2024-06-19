package com.spring_boot_template.application.usecase.project.impl;

import com.spring_boot_template.application.usecase.project.FetchTaskUseCase;
import com.spring_boot_template.application.usecase.project.FindTaskByTaskIdUseCase;
import com.spring_boot_template.application.usecase.project.converter.FetchTaskResponseConverter;
import com.spring_boot_template.domain.model.project.Project;
import com.spring_boot_template.domain.model.project.ProjectRepository;
import com.spring_boot_template.domain.model.project.task.value.TaskId;
import com.spring_boot_template.domain.model.project.value.ProjectId;
import com.spring_boot_template.presentation.controller.project.response.FetchTaskResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class FetchTaskUseCaseImpl implements FetchTaskUseCase {
    private final FindTaskByTaskIdUseCase findTaskByTaskIdUseCase;
    private final ProjectRepository projectRepository;

    @Override
    @Transactional
    public FetchTaskResponse execute(final String taskId) {
        final TaskId taskIdValue = new TaskId(taskId);
        //        final Task task = findTaskByTaskIdUseCase.execute(taskIdValue);
        final Project project =
                projectRepository.findProjectByProjectId(
                        new ProjectId("1123456789ABCDEFGHJKMNPQRS"));

        return FetchTaskResponseConverter.execute(project.getTasks().get(0));
    }
}
