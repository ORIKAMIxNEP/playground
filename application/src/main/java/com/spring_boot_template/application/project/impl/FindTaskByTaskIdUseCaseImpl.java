package com.spring_boot_template.application.project.impl;

import com.spring_boot_template.application.project.FindTaskByTaskIdUseCase;
import com.spring_boot_template.domain.model.project.task.Task;
import com.spring_boot_template.domain.model.project.task.value.TaskId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class FindTaskByTaskIdUseCaseImpl implements FindTaskByTaskIdUseCase {
    @Override
    @Transactional
    public Task execute(final TaskId taskId) {
        //        return Optional.ofNullable(taskRepository.fetchTaskByTaskId(taskId))
        //                .orElseThrow(() -> new ValidationException("Task is not found"));
        return null;
    }
}
