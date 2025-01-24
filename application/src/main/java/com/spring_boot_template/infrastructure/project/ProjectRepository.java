package com.spring_boot_template.infrastructure.project;

import static com.spring_boot_template.jooq.Tables.DUE_DATE_DETAILS;
import static com.spring_boot_template.jooq.Tables.PROJECT_ACCOUNT_PARTICIPATIONS;
import static com.spring_boot_template.jooq.Tables.TASKS;
import static com.spring_boot_template.jooq.Tables.TASK_ACCOUNT_ASSIGNMENTS;
import static com.spring_boot_template.jooq.tables.Projects.PROJECTS;

import com.spring_boot_template.domain.exception.ResourceNotFoundException;
import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.due_date_detail.DueDateDetail;
import com.spring_boot_template.domain.model.due_date_detail.value.DueDate;
import com.spring_boot_template.domain.model.due_date_detail.value.MaxPostponeCount;
import com.spring_boot_template.domain.model.due_date_detail.value.PostponeCount;
import com.spring_boot_template.domain.model.project.Project;
import com.spring_boot_template.domain.model.project.value.ProjectId;
import com.spring_boot_template.domain.model.project.value.ProjectName;
import com.spring_boot_template.domain.model.task.Task;
import com.spring_boot_template.domain.model.task.value.Status;
import com.spring_boot_template.domain.model.task.value.TaskId;
import com.spring_boot_template.domain.model.task.value.TaskName;
import com.spring_boot_template.infrastructure.due_date_detail.DueDateDetailDto;
import com.spring_boot_template.infrastructure.task.TaskDto;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class ProjectRepository implements com.spring_boot_template.domain.model.project.ProjectRepository {
    private final DSLContext dslContext;
    private final MessageSource messageSource;

    @Override
    public void saveProject(final Project project) {
        final String projectId = project.getProjectId().value();
        final String projectName = project.getProjectName().value();

        insertProject(projectId, projectName);

        final Set<AccountId> projectAccountParticipations = project.getAccountIds();

        deleteProjectAccountParticipations(projectId);
        projectAccountParticipations.forEach(
                projectAccountParticipation ->
                        insertProjectAccountParticipation(
                                projectId, projectAccountParticipation.value()));

        final LinkedHashSet<Task> tasks = project.getTasks();

        deleteTasks(projectId);
        tasks.forEach(
                task -> {
                    final String taskId = task.getTaskId().value();
                    final String taskName = task.getTaskName().value();
                    final String status = task.getStatus().toString();
                    final int index =
                            IntStream.range(0, tasks.size())
                                    .filter(i -> tasks.toArray()[i].equals(task))
                                    .findFirst()
                                    .orElse(-1);

                    insertTask(projectId, taskId, taskName, status, index);

                    final Set<AccountId> taskAccountAssignments = task.getAccountIds();

                    taskAccountAssignments.forEach(
                            taskAccountAssignment ->
                                    insertTaskAccountAssignment(
                                            taskId, taskAccountAssignment.value()));

                    final Optional<DueDateDetail> optionalDueDateDetail = task.getDueDateDetail();

                    optionalDueDateDetail.ifPresent(
                            dueDateDetail -> {
                                final LocalDateTime dueDate = dueDateDetail.getDueDate().value();
                                final int postponeCount = dueDateDetail.getPostponeCount().value();
                                final int maxPostponeCount =
                                        dueDateDetail.getMaxPostponeCount().value();

                                insertDueDateDetail(
                                        taskId, dueDate, postponeCount, maxPostponeCount);
                            });
                });
    }

    @Override
    public Project findProjectByProjectId(final ProjectId projectId) {
        final ProjectDto projectDto =
                Optional.ofNullable(selectProjectByProjectId(projectId.value()))
                        .orElseThrow(
                                () ->
                                        new ResourceNotFoundException(
                                                messageSource.getMessage(
                                                        "project.not-found",
                                                        null,
                                                        Locale.getDefault())));
        final ProjectName projectName = projectDto.projectName();
        final Set<AccountId> projectAccountParticipations =
                new HashSet<>(selectProjectAccountParticipationsByProjectId(projectId.value()));
        final LinkedHashSet<Task> tasks =
                selectTasksByProjectId(projectId.value()).stream()
                        .map(
                                taskDto -> {
                                    final TaskId taskId = taskDto.taskId();
                                    final TaskName taskName = taskDto.taskName();
                                    final Status status = taskDto.status();
                                    final Set<AccountId> taskAccountAssignments =
                                            new HashSet<>(
                                                    selectTaskAccountAssignmentsByTaskId(
                                                            taskId.value()));
                                    final DueDateDetail dueDateDetail =
                                            Optional.ofNullable(
                                                            selectDueDateDetailByTaskId(
                                                                    taskId.value()))
                                                    .map(
                                                            dueDateDetailDto -> {
                                                                final DueDate dueDate =
                                                                        new DueDate(
                                                                                dueDateDetailDto
                                                                                        .dueDate()
                                                                                        .toLocalDateTime());
                                                                final PostponeCount postponeCount =
                                                                        dueDateDetailDto
                                                                                .postponeCount();
                                                                final MaxPostponeCount
                                                                        maxPostponeCount =
                                                                                dueDateDetailDto
                                                                                        .maxPostponeCount();

                                                                return DueDateDetail
                                                                        .reconstructDueDateDetail(
                                                                                dueDate,
                                                                                postponeCount,
                                                                                maxPostponeCount);
                                                            })
                                                    .orElse(null);

                                    return Task.reconstructTask(
                                            taskId,
                                            taskName,
                                            status,
                                            taskAccountAssignments,
                                            dueDateDetail);
                                })
                        .collect(Collectors.toCollection(LinkedHashSet::new));

        return Project.reconstructProject(
                projectId, projectName, projectAccountParticipations, tasks);
    }

    @Override
    public void deleteProject(final ProjectId projectId) {
        dslContext.deleteFrom(PROJECTS).where(PROJECTS.PROJECT_ID.eq(projectId.value())).execute();
    }

    private void insertProject(final String projectId, final String projectName) {
        dslContext
                .insertInto(PROJECTS)
                .columns(PROJECTS.PROJECT_ID, PROJECTS.PROJECT_NAME)
                .values(projectId, projectName)
                .onConflict(PROJECTS.PROJECT_ID)
                .doUpdate()
                .set(PROJECTS.PROJECT_NAME, DSL.val(projectName))
                .execute();
    }

    private ProjectDto selectProjectByProjectId(final String projectId) {
        return dslContext
                .select(PROJECTS.PROJECT_NAME)
                .from(PROJECTS)
                .where(PROJECTS.PROJECT_ID.eq(projectId))
                .forUpdate()
                .fetchOneInto(ProjectDto.class);
    }

    private void insertProjectAccountParticipation(final String projectId, final String accountId) {
        dslContext
                .insertInto(PROJECT_ACCOUNT_PARTICIPATIONS)
                .columns(
                        PROJECT_ACCOUNT_PARTICIPATIONS.PROJECT_ID,
                        PROJECT_ACCOUNT_PARTICIPATIONS.ACCOUNT_ID)
                .values(projectId, accountId)
                .execute();
    }

    private List<AccountId> selectProjectAccountParticipationsByProjectId(final String projectId) {
        return dslContext
                .select(PROJECT_ACCOUNT_PARTICIPATIONS.ACCOUNT_ID)
                .from(PROJECT_ACCOUNT_PARTICIPATIONS)
                .where(PROJECT_ACCOUNT_PARTICIPATIONS.PROJECT_ID.eq(projectId))
                .forUpdate()
                .fetchInto(AccountId.class);
    }

    private void deleteProjectAccountParticipations(final String projectId) {
        dslContext
                .deleteFrom(PROJECT_ACCOUNT_PARTICIPATIONS)
                .where(PROJECT_ACCOUNT_PARTICIPATIONS.PROJECT_ID.eq(projectId))
                .execute();
    }

    private void insertTask(
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

    private List<TaskDto> selectTasksByProjectId(final String projectId) {
        return dslContext
                .select(TASKS.TASK_ID, TASKS.TASK_NAME, TASKS.STATUS)
                .from(TASKS)
                .where(TASKS.PROJECT_ID.eq(projectId))
                .orderBy(TASKS.INDEX)
                .forUpdate()
                .fetchInto(TaskDto.class);
    }

    private void deleteTasks(final String projectId) {
        dslContext.deleteFrom(TASKS).where(TASKS.PROJECT_ID.eq(projectId)).execute();
    }

    private void insertTaskAccountAssignment(final String taskId, final String accountId) {
        dslContext
                .insertInto(TASK_ACCOUNT_ASSIGNMENTS)
                .columns(TASK_ACCOUNT_ASSIGNMENTS.TASK_ID, TASK_ACCOUNT_ASSIGNMENTS.ACCOUNT_ID)
                .values(taskId, accountId)
                .execute();
    }

    private List<AccountId> selectTaskAccountAssignmentsByTaskId(final String taskId) {
        return dslContext
                .select(TASK_ACCOUNT_ASSIGNMENTS.ACCOUNT_ID)
                .from(TASK_ACCOUNT_ASSIGNMENTS)
                .where(TASK_ACCOUNT_ASSIGNMENTS.TASK_ID.eq(taskId))
                .forUpdate()
                .fetchInto(AccountId.class);
    }

    private void insertDueDateDetail(
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

    private DueDateDetailDto selectDueDateDetailByTaskId(final String taskId) {
        return dslContext
                .select(
                        DUE_DATE_DETAILS.DUE_DATE,
                        DUE_DATE_DETAILS.POSTPONE_COUNT,
                        DUE_DATE_DETAILS.MAX_POSTPONE_COUNT)
                .from(DUE_DATE_DETAILS)
                .where(DUE_DATE_DETAILS.TASK_ID.eq(taskId))
                .forUpdate()
                .fetchOneInto(DueDateDetailDto.class);
    }
}
