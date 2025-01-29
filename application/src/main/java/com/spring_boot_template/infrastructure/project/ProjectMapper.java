package com.spring_boot_template.infrastructure.project;

import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.infrastructure.due_date_detail.DueDateDetailDto;
import com.spring_boot_template.infrastructure.task.TaskDto;
import java.time.LocalDateTime;
import java.util.List;

interface ProjectMapper {
    void insertProject(final String projectId, final String projectName);

    ProjectDto selectProjectByProjectId(final String projectId);

    void deleteProject(final String projectId);

    void insertProjectAccountParticipation(
            final String projectId, final String participatingAccountId);

    List<AccountId> selectProjectAccountParticipationsByProjectId(final String projectId);

    void deleteProjectAccountParticipations(final String projectId);

    void insertTask(
            final String projectId,
            final String taskId,
            final String taskName,
            final String status,
            final int index);

    List<TaskDto> selectTasksByProjectId(final String projectId);

    void deleteTasks(final String projectId);

    void insertTaskAccountAssignment(final String taskId, final String assignedAccountId);

    List<AccountId> selectTaskAccountAssignmentsByTaskId(final String taskId);

    void insertDueDateDetail(
            final String taskId,
            final LocalDateTime dueDate,
            final int postponeCount,
            final int maxPostponeCount);

    DueDateDetailDto selectDueDateDetailByTaskId(final String taskId);
}
