package com.spring_boot_template.application.usecase.project.impl;

import com.spring_boot_template.application.usecase.project.FindTaskByTaskIdUseCase;
import com.spring_boot_template.domain.exception.ValidationException;
import com.spring_boot_template.domain.model.task.Task;
import com.spring_boot_template.domain.model.task.value.TaskId;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class FindTaskByTaskIdUseCaseImpl implements FindTaskByTaskIdUseCase {
    private final TaskRepository taskRepository;

    @Override
    @Transactional
    public Task execute(final TaskId taskId) {
        return Optional.ofNullable(taskRepository.fetchTaskByTaskId(taskId))
                .orElseThrow(() -> new ValidationException("Task is not found"));
    }
}
