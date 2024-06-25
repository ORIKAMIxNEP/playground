package com.spring_boot_template.infrastructure.project.task;

import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.project.task.Task;
import com.spring_boot_template.domain.model.project.task.value.TaskId;
import com.spring_boot_template.domain.model.project.value.ProjectId;
import com.spring_boot_template.infrastructure.project.task.dto.TaskDto;
import java.util.ArrayList;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TaskMapper {
    void insertTask(final ProjectId projectId, final Task task, final int index);

    void insertAssignedAccount(final TaskId taskId, final AccountId assignedAccountId);

    ArrayList<TaskDto> selectTasksByProjectId(final ProjectId projectId);

    void deleteTasks(final ProjectId projectId);
}
