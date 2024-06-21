package com.spring_boot_template.infrastructure.project;

import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.project.Project;
import com.spring_boot_template.domain.model.project.task.Task;
import com.spring_boot_template.domain.model.project.task.due_date_detail.DueDateDetail;
import com.spring_boot_template.domain.model.project.task.value.TaskId;
import com.spring_boot_template.domain.model.project.value.ProjectId;
import com.spring_boot_template.infrastructure.project.dto.DueDateDetailDto;
import com.spring_boot_template.infrastructure.project.dto.ProjectDto;
import com.spring_boot_template.infrastructure.project.dto.TaskDto;
import java.util.ArrayList;
import org.apache.ibatis.annotations.Mapper;

@Mapper
interface ProjectMapper {
    void upsertProject(final Project project);

    void insertProjectParticipatingAccount(
            final ProjectId projectId, final AccountId participatingAccountId);

    void insertTask(final ProjectId projectId, final Task task, final int index);

    void insertTaskAssignedAccount(final TaskId taskId, final AccountId assignedAccountId);

    void insertDueDateDetail(final TaskId taskId, final DueDateDetail dueDateDetail);

    ProjectDto selectProjectByProjectId(final ProjectId projectId);

    ArrayList<TaskDto> selectTasksByProjectId(final ProjectId projectId);

    ArrayList<DueDateDetailDto> selectDueDateDetailsByProjectId(final ProjectId projectId);

    void deleteProject(final ProjectId projectId);

    void deleteProjectParticipatingAccount(final ProjectId projectId);

    void deleteTask(final ProjectId projectId);
}
