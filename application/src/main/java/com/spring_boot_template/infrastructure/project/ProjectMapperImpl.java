package com.spring_boot_template.infrastructure.project;

import static com.spring_boot_template.jooq.Tables.DUE_DATE_DETAILS;
import static com.spring_boot_template.jooq.Tables.PROJECTS;
import static com.spring_boot_template.jooq.Tables.PROJECT_ACCOUNT_PARTICIPATIONS;
import static com.spring_boot_template.jooq.Tables.TASKS;
import static com.spring_boot_template.jooq.Tables.TASK_ACCOUNT_ASSIGNMENTS;

import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.infrastructure.due_date_detail.DueDateDetailDto;
import com.spring_boot_template.infrastructure.task.TaskDto;
import com.spring_boot_template.jooq.tables.Projects;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
final class ProjectMapperImpl implements ProjectMapper {
    private final DSLContext dslContext;

    @Override
    public void insertProject(final String projectId, final String projectName) {
        dslContext
                .insertInto(PROJECTS)
                .columns(PROJECTS.PROJECT_ID, PROJECTS.PROJECT_NAME)
                .values(projectId, projectName)
                .onConflict(PROJECTS.PROJECT_ID)
                .doUpdate()
                .set(PROJECTS.PROJECT_NAME, DSL.val(projectName))
                .execute();
    }

    @Override
    public Optional<ProjectDto> selectProjectByProjectId(final String projectId) {
        return Optional.ofNullable(
                dslContext
                        .select(PROJECTS.PROJECT_NAME)
                        .from(PROJECTS)
                        .where(PROJECTS.PROJECT_ID.eq(projectId))
                        .forUpdate()
                        .fetchOneInto(ProjectDto.class));
    }

    @Override
    public void deleteProject(final String projectId) {
        dslContext
                .deleteFrom(Projects.PROJECTS)
                .where(Projects.PROJECTS.PROJECT_ID.eq(projectId))
                .execute();
    }

    @Override
    public void insertProjectAccountParticipation(
            final String projectId, final String participatingAccountId) {
        dslContext
                .insertInto(PROJECT_ACCOUNT_PARTICIPATIONS)
                .columns(
                        PROJECT_ACCOUNT_PARTICIPATIONS.PROJECT_ID,
                        PROJECT_ACCOUNT_PARTICIPATIONS.PARTICIPATING_ACCOUNT_ID)
                .values(projectId, participatingAccountId)
                .execute();
    }

    @Override
    public List<AccountId> selectProjectAccountParticipationsByProjectId(final String projectId) {
        return dslContext
                .select(PROJECT_ACCOUNT_PARTICIPATIONS.PARTICIPATING_ACCOUNT_ID)
                .from(PROJECT_ACCOUNT_PARTICIPATIONS)
                .where(PROJECT_ACCOUNT_PARTICIPATIONS.PROJECT_ID.eq(projectId))
                .forUpdate()
                .fetchInto(AccountId.class);
    }

    @Override
    public void deleteProjectAccountParticipations(final String projectId) {
        dslContext
                .deleteFrom(PROJECT_ACCOUNT_PARTICIPATIONS)
                .where(PROJECT_ACCOUNT_PARTICIPATIONS.PROJECT_ID.eq(projectId))
                .execute();
    }

    @Override
    public void insertTask(
            final String projectId,
            final String taskId,
            final String taskName,
            final String status,
            final int index) {
        dslContext
                .insertInto(TASKS)
                .columns(
                        TASKS.PROJECT_ID, TASKS.TASK_ID, TASKS.TASK_NAME, TASKS.STATUS, TASKS.INDEX)
                .values(projectId, taskId, taskName, status, index)
                .execute();
    }

    @Override
    public List<TaskDto> selectTasksByProjectId(final String projectId) {
        return dslContext
                .select(TASKS.TASK_ID, TASKS.TASK_NAME, TASKS.STATUS)
                .from(TASKS)
                .where(TASKS.PROJECT_ID.eq(projectId))
                .orderBy(TASKS.INDEX)
                .forUpdate()
                .fetchInto(TaskDto.class);
    }

    @Override
    public void deleteTasks(final String projectId) {
        dslContext.deleteFrom(TASKS).where(TASKS.PROJECT_ID.eq(projectId)).execute();
    }

    @Override
    public void insertTaskAccountAssignment(final String taskId, final String assignedAccountId) {
        dslContext
                .insertInto(TASK_ACCOUNT_ASSIGNMENTS)
                .columns(
                        TASK_ACCOUNT_ASSIGNMENTS.TASK_ID,
                        TASK_ACCOUNT_ASSIGNMENTS.ASSIGNED_ACCOUNT_ID)
                .values(taskId, assignedAccountId)
                .execute();
    }

    @Override
    public List<AccountId> selectTaskAccountAssignmentsByTaskId(final String taskId) {
        return dslContext
                .select(TASK_ACCOUNT_ASSIGNMENTS.ASSIGNED_ACCOUNT_ID)
                .from(TASK_ACCOUNT_ASSIGNMENTS)
                .where(TASK_ACCOUNT_ASSIGNMENTS.TASK_ID.eq(taskId))
                .forUpdate()
                .fetchInto(AccountId.class);
    }

    @Override
    public void insertDueDateDetail(
            final String taskId,
            final LocalDateTime dueDate,
            final int postponeCount,
            final int maxPostponeCount) {
        dslContext
                .insertInto(
                        DUE_DATE_DETAILS,
                        DUE_DATE_DETAILS.TASK_ID,
                        DUE_DATE_DETAILS.DUE_DATE,
                        DUE_DATE_DETAILS.POSTPONE_COUNT,
                        DUE_DATE_DETAILS.MAX_POSTPONE_COUNT)
                .values(taskId, dueDate, postponeCount, maxPostponeCount)
                .execute();
    }

    @Override
    public Optional<DueDateDetailDto> selectDueDateDetailByTaskId(final String taskId) {
        return Optional.ofNullable(
                dslContext
                        .select(
                                DUE_DATE_DETAILS.DUE_DATE,
                                DUE_DATE_DETAILS.POSTPONE_COUNT,
                                DUE_DATE_DETAILS.MAX_POSTPONE_COUNT)
                        .from(DUE_DATE_DETAILS)
                        .where(DUE_DATE_DETAILS.TASK_ID.eq(taskId))
                        .forUpdate()
                        .fetchOneInto(DueDateDetailDto.class));
    }
}
