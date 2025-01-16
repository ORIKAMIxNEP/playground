package com.spring_boot_template.infrastructure.task;

import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.project.value.ProjectId;
import com.spring_boot_template.domain.model.task.Task;
import com.spring_boot_template.domain.model.task.value.TaskId;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TaskMapper {
    void insertTask(
            @Param("projectId") final ProjectId projectId, final Task task, final int index);

    void insertAccountId(
            @Param("taskId") final TaskId taskId, @Param("accountId") final AccountId accountId);

    List<TaskDto> selectTasksByProjectId(@Param("projectId") final ProjectId projectId);

    void deleteTasks(@Param("projectId") final ProjectId projectId);
}
