package com.spring_boot_template.application.usecase.task.impl;

import com.spring_boot_template.application.usecase.task.FindTaskByTaskIdUseCase;
import com.spring_boot_template.domain.exception.ValidationException;
import com.spring_boot_template.domain.model.task.TaskEntity;
import com.spring_boot_template.domain.model.task.TaskRepository;
import com.spring_boot_template.domain.model.task.value.TaskIdValue;
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
    public TaskEntity execute(final TaskIdValue taskId) {
        return Optional.ofNullable(taskRepository.fetchTaskByTaskId(taskId))
                .orElseThrow(() -> new ValidationException("Task is not found"));
    }
}
