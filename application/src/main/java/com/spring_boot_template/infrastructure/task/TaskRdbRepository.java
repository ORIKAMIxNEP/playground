package com.spring_boot_template.infrastructure.task;

import com.spring_boot_template.domain.model.task.TaskEntity;
import com.spring_boot_template.domain.model.task.TaskRepository;
import com.spring_boot_template.domain.model.task.value.TaskIdValue;
import com.spring_boot_template.domain.model.user.value.UserIdValue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TaskRdbRepository implements TaskRepository {
    private final TaskMapper taskMapper;

    @Override
    public void addTask(final TaskEntity task) {
        taskMapper.insertTask(task);
    }

    @Override
    public TaskEntity fetchTaskByTaskId(final TaskIdValue taskId) {
        return taskMapper.selectTaskByTaskId(taskId);
    }

    @Override
    public int countTaskByUserId(final UserIdValue userId) {
        return taskMapper.selectCountTaskByUserId(userId);
    }

    @Override
    public void updateTaskStatus(final TaskEntity task) {
        taskMapper.updateTaskStatus(task);
    }

    @Override
    public void deleteTask(final TaskIdValue taskId) {
        taskMapper.deleteTask(taskId);
    }
}
