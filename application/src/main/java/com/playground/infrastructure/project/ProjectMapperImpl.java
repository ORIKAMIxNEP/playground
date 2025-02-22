package com.playground.infrastructure.project;

import static com.playground.jooq.Tables.DEADLINES;
import static com.playground.jooq.Tables.PROJECTS;
import static com.playground.jooq.Tables.PROJECT_ACCOUNT_PARTICIPATIONS;
import static com.playground.jooq.Tables.TASKS;
import static com.playground.jooq.Tables.TASK_ACCOUNT_ASSIGNMENTS;

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
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
final class ProjectMapperImpl implements ProjectMapper {
  private final DSLContext dslContext;

  @Override
  public void insertProject(final ProjectId projectId, final ProjectName projectName) {
    dslContext
        .insertInto(PROJECTS)
        .columns(PROJECTS.PROJECT_ID, PROJECTS.PROJECT_NAME)
        .values(projectId.value(), projectName.value())
        .onConflict(PROJECTS.PROJECT_ID)
        .doUpdate()
        .set(PROJECTS.PROJECT_NAME, projectName.value())
        .execute();
  }

  @Override
  public Optional<ProjectDto> selectProjectByProjectId(final ProjectId projectId) {
    return Optional.ofNullable(
        dslContext
            .select(PROJECTS.PROJECT_NAME)
            .from(PROJECTS)
            .where(PROJECTS.PROJECT_ID.eq(projectId.value()))
            .forUpdate()
            .fetchOneInto(ProjectDto.class));
  }

  @Override
  public void deleteProject(final ProjectId projectId) {
    dslContext.deleteFrom(PROJECTS).where(PROJECTS.PROJECT_ID.eq(projectId.value())).execute();
  }

  @Override
  public void insertProjectAccountParticipation(
      final ProjectId projectId, final AccountId participatingAccountId) {
    dslContext
        .insertInto(PROJECT_ACCOUNT_PARTICIPATIONS)
        .columns(
            PROJECT_ACCOUNT_PARTICIPATIONS.PROJECT_ID,
            PROJECT_ACCOUNT_PARTICIPATIONS.PARTICIPATING_ACCOUNT_ID)
        .values(projectId.value(), participatingAccountId.value())
        .execute();
  }

  @Override
  public List<AccountId> selectProjectAccountParticipationsByProjectId(final ProjectId projectId) {
    return dslContext
        .select(PROJECT_ACCOUNT_PARTICIPATIONS.PARTICIPATING_ACCOUNT_ID)
        .from(PROJECT_ACCOUNT_PARTICIPATIONS)
        .where(PROJECT_ACCOUNT_PARTICIPATIONS.PROJECT_ID.eq(projectId.value()))
        .forUpdate()
        .fetchInto(AccountId.class);
  }

  @Override
  public void deleteProjectAccountParticipations(final ProjectId projectId) {
    dslContext
        .deleteFrom(PROJECT_ACCOUNT_PARTICIPATIONS)
        .where(PROJECT_ACCOUNT_PARTICIPATIONS.PROJECT_ID.eq(projectId.value()))
        .execute();
  }

  @Override
  public void insertTask(
      final ProjectId projectId,
      final TaskId taskId,
      final TaskName taskName,
      final Status status,
      final int index) {
    dslContext
        .insertInto(TASKS)
        .columns(TASKS.PROJECT_ID, TASKS.TASK_ID, TASKS.TASK_NAME, TASKS.STATUS, TASKS.INDEX)
        .values(projectId.value(), taskId.value(), taskName.value(), status.toString(), index)
        .execute();
  }

  @Override
  public List<TaskDto> selectTasksByProjectId(final ProjectId projectId) {
    return dslContext
        .select(TASKS.TASK_ID, TASKS.TASK_NAME, TASKS.STATUS)
        .from(TASKS)
        .where(TASKS.PROJECT_ID.eq(projectId.value()))
        .orderBy(TASKS.INDEX)
        .forUpdate()
        .fetchInto(TaskDto.class);
  }

  @Override
  public void deleteTasks(final ProjectId projectId) {
    dslContext.deleteFrom(TASKS).where(TASKS.PROJECT_ID.eq(projectId.value())).execute();
  }

  @Override
  public void insertTaskAccountAssignment(final TaskId taskId, final AccountId assignedAccountId) {
    dslContext
        .insertInto(TASK_ACCOUNT_ASSIGNMENTS)
        .columns(TASK_ACCOUNT_ASSIGNMENTS.TASK_ID, TASK_ACCOUNT_ASSIGNMENTS.ASSIGNED_ACCOUNT_ID)
        .values(taskId.value(), assignedAccountId.value())
        .execute();
  }

  @Override
  public List<AccountId> selectTaskAccountAssignmentsByTaskId(final TaskId taskId) {
    return dslContext
        .select(TASK_ACCOUNT_ASSIGNMENTS.ASSIGNED_ACCOUNT_ID)
        .from(TASK_ACCOUNT_ASSIGNMENTS)
        .where(TASK_ACCOUNT_ASSIGNMENTS.TASK_ID.eq(taskId.value()))
        .forUpdate()
        .fetchInto(AccountId.class);
  }

  @Override
  public void insertDeadline(
      final TaskId taskId,
      final DueDate dueDate,
      final PostponeCount postponeCount,
      final MaxPostponeCount maxPostponeCount) {
    dslContext
        .insertInto(
            DEADLINES,
            DEADLINES.TASK_ID,
            DEADLINES.DUE_DATE,
            DEADLINES.POSTPONE_COUNT,
            DEADLINES.MAX_POSTPONE_COUNT)
        .values(taskId.value(), dueDate.value(), postponeCount.value(), maxPostponeCount.value())
        .execute();
  }

  @Override
  public Optional<DeadlineDto> selectDeadlineByTaskId(final TaskId taskId) {
    return Optional.ofNullable(
        dslContext
            .select(DEADLINES.DUE_DATE, DEADLINES.POSTPONE_COUNT, DEADLINES.MAX_POSTPONE_COUNT)
            .from(DEADLINES)
            .where(DEADLINES.TASK_ID.eq(taskId.value()))
            .forUpdate()
            .fetchOneInto(DeadlineDto.class));
  }
}
