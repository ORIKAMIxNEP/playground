package com.spring_boot_template.application.project.impl;

import com.spring_boot_template.application.project.DeleteTaskUseCase;
import com.spring_boot_template.presentation.controller.project.request.DeleteTaskRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class DeleteTaskUseCaseImpl implements DeleteTaskUseCase {
    @Override
    @Transactional
    public void execute(final DeleteTaskRequest deleteTaskRequest) {
        //        final TaskId taskId = new TaskId(deleteTaskRequest.taskId());
        //
        //        findTaskByTaskIdUseCase.execute(taskId);
        //        taskRepository.deleteTask(taskId);
    }
}
