package com.spring_boot_template.application.usecase.task.impl;

import com.spring_boot_template.application.usecase.task.AddTaskUseCase;
import com.spring_boot_template.domain.model.task.TaskRepository;
import com.spring_boot_template.domain.model.task.factory.TaskFactory;
import com.spring_boot_template.presentation.controller.task.request.AddTaskRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AddTaskUseCaseImpl implements AddTaskUseCase {
    private final TaskFactory taskFactory;
    private final TaskRepository recordRepository;

    @Override
    @Transactional
    public void execute(final AddTaskRequest addTaskRequest) {
        recordRepository.addTask(null);
    }
}
