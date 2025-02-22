package com.playground.infrastructure.project;

import com.playground.domain.model.account.value.AccountId;
import com.playground.domain.model.deadline.value.DueDate;
import com.playground.domain.model.deadline.value.MaxPostponeCount;
import com.playground.domain.model.deadline.value.PostponeCount;
import com.playground.domain.model.project.value.ProjectId;
import com.playground.domain.model.project.value.ProjectName;
import com.playground.domain.model.task.value.Status;
import com.playground.domain.model.task.value.TaskId;
import com.playground.domain.model.task.value.TaskName;
import com.playground.infrastructure.deadline.DeadlineDto;
import com.playground.infrastructure.task.TaskDto;
import java.util.List;
import java.util.Optional;

interface ProjectMapper {
  void insertProject(final ProjectId projectId, final ProjectName projectName);

  Optional<ProjectDto> selectProjectByProjectId(final ProjectId projectId);

  void deleteProject(final ProjectId projectId);

  void insertProjectAccountParticipation(
      final ProjectId projectId, final AccountId participatingAccountId);

  List<AccountId> selectProjectAccountParticipationsByProjectId(final ProjectId projectId);

  void deleteProjectAccountParticipations(final ProjectId projectId);

  void insertTask(
      final ProjectId projectId,
      final TaskId taskId,
      final TaskName taskName,
      final Status status,
      final int index);

  List<TaskDto> selectTasksByProjectId(final ProjectId projectId);

  void deleteTasks(final ProjectId projectId);

  void insertTaskAccountAssignment(final TaskId taskId, final AccountId assignedAccountId);

  List<AccountId> selectTaskAccountAssignmentsByTaskId(final TaskId taskId);

  void insertDeadline(
      final TaskId taskId,
      final DueDate dueDate,
      final PostponeCount postponeCount,
      final MaxPostponeCount maxPostponeCount);

  Optional<DeadlineDto> selectDeadlineByTaskId(final TaskId taskId);
}
