package com.spring_boot_template.domain.model.task.factory;

import com.spring_boot_template.domain.model.task.TaskEntity;
import com.spring_boot_template.domain.model.task.TaskRepository;
import com.spring_boot_template.domain.model.task.value.DueDateValue;
import com.spring_boot_template.domain.model.task.value.MaxPostponeCountValue;
import com.spring_boot_template.domain.model.task.value.PostponeCountValue;
import com.spring_boot_template.domain.model.task.value.TaskIdValue;
import com.spring_boot_template.domain.model.task.value.TaskNameValue;
import com.spring_boot_template.domain.model.task.value.TaskStatusValue;
import com.spring_boot_template.domain.model.user.value.UserIdValue;
import com.spring_boot_template.domain.shared.IdGenerator;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public final class TaskFactoryImpl implements TaskFactory {
    private final TaskRepository taskRepository;

    private static final int TASK_ASSIGNMENT_LIMIT = 10;

    @Override
    public TaskEntity createTask(
            final TaskNameValue taskName,
            final UserIdValue userId,
            final DueDateValue dueDate,
            final MaxPostponeCountValue maxPostponeCount) {
        // 割り当てられているタスクの数が限界である場合
        if (taskRepository.countTaskByUserId(userId) >= TASK_ASSIGNMENT_LIMIT) {
            throw new ValidationException("Reached task assignment limit");
        }

        final TaskIdValue taskId = new TaskIdValue(IdGenerator.generate());
        final TaskStatusValue taskStatus = TaskStatusValue.UNDONE;
        final PostponeCountValue postponeCount = new PostponeCountValue(0);

        return new TaskEntity(
                taskId, taskName, taskStatus, userId, dueDate, postponeCount, maxPostponeCount);
    }
}
