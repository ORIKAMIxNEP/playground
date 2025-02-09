package com.playground.infrastructure.project;

import com.playground.domain.model.account.value.AccountId;
import com.playground.infrastructure.deadline.DeadlineDto;
import com.playground.infrastructure.task.TaskDto;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

interface ProjectMapper {
  void insertProject(final String projectId, final String projectName);

  Optional<ProjectDto> selectProjectByProjectId(final String projectId);

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

  void insertDeadline(
      final String taskId,
      final LocalDateTime dueDate,
      final int postponeCount,
      final int maxPostponeCount);

  Optional<DeadlineDto> selectDeadlineByTaskId(final String taskId);
}
