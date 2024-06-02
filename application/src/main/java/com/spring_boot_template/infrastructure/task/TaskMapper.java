package com.spring_boot_template.infrastructure.task;

import com.spring_boot_template.domain.model.task.TaskEntity;
import com.spring_boot_template.domain.model.task.value.TaskIdValue;
import com.spring_boot_template.domain.model.user.value.UserIdValue;
import org.apache.ibatis.annotations.Mapper;

@Mapper
interface TaskMapper {
    void insertTask(final TaskEntity taskEntity);

    TaskEntity selectTaskByTaskId(final TaskIdValue taskId);

    int selectCountTaskByUserId(final UserIdValue userId);

    void updateTaskName(final TaskEntity task);

    void deleteTask(final TaskIdValue taskId);
}
