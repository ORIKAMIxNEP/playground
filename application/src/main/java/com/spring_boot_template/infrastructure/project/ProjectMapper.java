package com.spring_boot_template.infrastructure.project;

import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.project.Project;
import com.spring_boot_template.domain.model.project.value.ProjectId;
import com.spring_boot_template.domain.model.task.Task;
import com.spring_boot_template.domain.model.task.value.TaskId;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
interface ProjectMapper {
    void insert(final Project project, final List<AccountId> participatedAccountIds);

    void insertTask(
            final ProjectId id,
            final Task task,
            final List<AccountId> assignedAccountIds,
            final int index);

    Project selectById(final ProjectId id);

    void update(final Project project);

    void updateTask(final Task task);

    void delete(final ProjectId id);

    void deleteTaskByProjectId(final ProjectId projectId);

    void deleteTaskByTaskId(final TaskId taskId);
}
