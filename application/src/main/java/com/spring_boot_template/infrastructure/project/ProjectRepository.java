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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class ProjectRepository implements com.spring_boot_template.domain.model.project.ProjectRepository {
    private final DSLContext dslContext;

    @Override
    public void saveProject(final Project project) {
        final String projectId = project.getProjectId().value();
        final String projectName = project.getProjectName().value();
        insertProject(projectId, projectName);

        deleteProjectAccountParticipations(projectId);
        final Set<AccountId> participatingAccountIds = project.getAccountIds();
        participatingAccountIds.forEach(
                accountId -> insertProjectAccountParticipation(projectId, accountId.value()));

        deleteTasks(projectId);
        final LinkedHashSet<Task> tasks = (LinkedHashSet<Task>) project.getTasks();
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

                    final Set<AccountId> assignedAccountIds = task.getAccountIds();
                    assignedAccountIds.forEach(
                            accountId -> insertTaskAccountAssignment(taskId, accountId.value()));

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
        final List<ProjectDto> projectDtos = selectProjectByProjectId(projectId.value());
        if (projectDtos.isEmpty()) {
            throw new ResourceNotFoundException("Project is not found");
        }
        final ProjectDto projectDto = projectDtos.get(0);
        final ProjectName projectName = projectDto.projectName();
        final Set<AccountId> participatingAccountIds =
                projectDtos.stream().map(ProjectDto::accountId).collect(Collectors.toSet());

        final List<TaskDto> taskDtos = selectTasksByProjectId(projectId.value());
        final Set<Task> tasks =
                taskDtos.stream()
                        .collect(
                                Collectors.groupingBy(
                                        TaskDto::taskId,
                                        Collectors.collectingAndThen(
                                                Collectors.toList(),
                                                list -> {
                                                    final TaskDto taskDto = list.get(0);
                                                    final TaskName taskName = taskDto.taskName();
                                                    final Status status = taskDto.status();
                                                    System.out.println("a");
                                                    System.out.println(list);
                                                    final Set<AccountId> assignedAccountIds =
                                                            list.stream()
                                                                    .map(TaskDto::accountId)
                                                                    .collect(Collectors.toSet());
                                                    return Map.entry(
                                                            Map.entry(taskName, status),
                                                            assignedAccountIds);
                                                })))
                        .entrySet()
                        .stream()
                        .map(
                                entry -> {
                                    final TaskId taskId = entry.getKey();
                                    final TaskName taskName = entry.getValue().getKey().getKey();
                                    final Status status = entry.getValue().getKey().getValue();
                                    final Set<AccountId> assignedAccountIds =
                                            entry.getValue().getValue();

                                    final List<DueDateDetailDto> dueDateDetailDtos =
                                            selectDueDateDetailsByProjectId(projectId.value());
                                    final DueDateDetail dueDateDetail =
                                            dueDateDetailDtos.stream()
                                                    .filter(
                                                            dueDateDetailDto ->
                                                                    dueDateDetailDto
                                                                            .taskId()
                                                                            .equals(taskId))
                                                    .findFirst()
                                                    .map(
                                                            dueDateDetailDto -> {
                                                                final DueDate dueDate =
                                                                        dueDateDetailDto.dueDate();
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
                                            assignedAccountIds,
                                            dueDateDetail);
                                })
                        .collect(Collectors.toCollection(LinkedHashSet::new));

        return Project.reconstructProject(projectId, projectName, participatingAccountIds, tasks);
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

    private List<ProjectDto> selectProjectByProjectId(final String projectId) {
        return dslContext
                .select(PROJECTS.PROJECT_NAME, PROJECT_ACCOUNT_PARTICIPATIONS.ACCOUNT_ID)
                .from(PROJECTS)
                .innerJoin(PROJECT_ACCOUNT_PARTICIPATIONS)
                .on(PROJECTS.PROJECT_ID.eq(PROJECT_ACCOUNT_PARTICIPATIONS.PROJECT_ID))
                .where(PROJECTS.PROJECT_ID.eq(projectId))
                .forUpdate()
                .fetchInto(ProjectDto.class);
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
                .select(
                        TASKS.TASK_ID,
                        TASKS.TASK_NAME,
                        TASKS.STATUS,
                        TASK_ACCOUNT_ASSIGNMENTS.ACCOUNT_ID)
                .from(TASKS)
                .leftJoin(TASK_ACCOUNT_ASSIGNMENTS)
                .on(TASKS.TASK_ID.eq(TASK_ACCOUNT_ASSIGNMENTS.TASK_ID))
                .where(TASKS.PROJECT_ID.eq(projectId))
                .orderBy(TASKS.INDEX)
                .forUpdate()
                .of(TASKS)
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

    private List<DueDateDetailDto> selectDueDateDetailsByProjectId(final String projectId) {
        return dslContext
                .select(
                        DUE_DATE_DETAILS.TASK_ID,
                        DUE_DATE_DETAILS.DUE_DATE,
                        DUE_DATE_DETAILS.POSTPONE_COUNT,
                        DUE_DATE_DETAILS.MAX_POSTPONE_COUNT)
                .from(DUE_DATE_DETAILS)
                .where(DUE_DATE_DETAILS.TASK_ID.eq(projectId))
                .forUpdate()
                .fetchInto(DueDateDetailDto.class);
    }
}
