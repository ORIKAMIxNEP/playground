package com.spring_boot_template.infrastructure.project;

import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.task.Task;
import com.spring_boot_template.domain.model.task.value.TaskId;
import org.apache.ibatis.annotations.Mapper;

@Mapper
interface ProjectMapper {
    void insertTask(final Task task);

    Task selectTaskByTaskId(final TaskId taskId);

    int selectCountTaskByUserId(final AccountId userId);

    void updateTaskStatus(final Task task);

    void deleteTask(final TaskId taskId);
}
