package com.spring_boot_template.infrastructure.project.task;

import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.project.task.Task;
import com.spring_boot_template.domain.model.project.task.value.TaskId;
import com.spring_boot_template.domain.model.project.value.ProjectId;
import com.spring_boot_template.infrastructure.project.task.dto.TaskDto;
import java.util.ArrayList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TaskMapper {
    void insertTask(
            @Param("projectId") final ProjectId projectId, final Task task, final int index);

    void insertAssignedAccount(
            @Param("taskId") final TaskId taskId,
            @Param("assignedAccountId") final AccountId assignedAccountId);

    ArrayList<TaskDto> selectTasksByProjectId(@Param("projectId") final ProjectId projectId);

    void deleteTasks(@Param("projectId") final ProjectId projectId);
}
