package com.spring_boot_template.infrastructure.project;

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
import com.spring_boot_template.infrastructure.due_date_detail.DueDateDetailMapper;
import com.spring_boot_template.infrastructure.task.TaskDto;
import com.spring_boot_template.infrastructure.task.TaskMapper;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.set.ListOrderedSet;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
final class ProjectRepository
        implements com.spring_boot_template.domain.model.project.ProjectRepository {
    private final DSLContext dslContext;
    private final ProjectMapper projectMapper;
    private final TaskMapper taskMapper;
    private final DueDateDetailMapper dueDateDetailMapper;

    @Override
    public void saveProject(final Project project) {
        final String projectId = project.getProjectId().value();
        final String projectName = project.getProjectName().value();
        insertProject(projectId, projectName);

        deleteProjectAccountParticipations(projectId);
        final Set<AccountId> participatingAccountIds = project.getAccountIds();
        participatingAccountIds.forEach(
                accountId -> insertProjectAccountParticipation(projectId, accountId.value()));

        deleteTask(projectId);
        final ListOrderedSet<Task> tasks = (ListOrderedSet<Task>) project.getTasks();
        tasks.forEach(
                task -> {
                    final String taskId = task.getTaskId().value();
                    final String taskName = task.getTaskName().value();
                    final String status = task.getStatus().toString();
                    final int index = tasks.indexOf(task);
                    insertTask(projectId, taskId, taskName, status, index);

                    final Set<AccountId> assignedAccountIds = task.getAccountIds();
                    assignedAccountIds.forEach(
                            accountId -> insertTaskAccountAssignment(taskId, accountId.value()));

                    final Optional<DueDateDetail> optionalDueDateDetail = task.getDueDateDetail();
                    optionalDueDateDetail.ifPresent(
                            dueDateDetail -> {
                                final String dueDate = dueDateDetail.getDueDate().toString();
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
        final ProjectDto projectDto = projectMapper.selectProjectByProjectId(projectId);

        if (Objects.isNull(projectDto)) {
            throw new ResourceNotFoundException("Project is not found");
        }

        final ProjectName projectName = projectDto.getProjectName();
        final Set<AccountId> participatingAccountIds = projectDto.getAccountIds();

        final List<TaskDto> taskDtos = taskMapper.selectTasksByProjectId(projectId);
        final List<DueDateDetailDto> dueDateDetailDtos =
                dueDateDetailMapper.selectDueDateDetailsByProjectId(projectId);

        final Set<Task> tasks = new ListOrderedSet<>();
        taskDtos.stream()
                .map(
                        taskDto -> {
                            final TaskId taskId = taskDto.getTaskId();
                            final TaskName taskName = taskDto.getTaskName();
                            final Status status = taskDto.getStatus();
                            final Set<AccountId> assignedAccountIds = taskDto.getAccountIds();
                            final DueDateDetail dueDateDetail =
                                    dueDateDetailDtos.stream()
                                            .filter(
                                                    dueDateDetailDto ->
                                                            dueDateDetailDto
                                                                    .getTaskId()
                                                                    .value()
                                                                    .equals(taskId.value()))
                                            .findFirst()
                                            .map(
                                                    dueDateDetailDto -> {
                                                        final DueDate dueDate =
                                                                new DueDate(
                                                                        dueDateDetailDto
                                                                                .getDueDate()
                                                                                .toLocalDateTime());
                                                        final PostponeCount postponeCount =
                                                                dueDateDetailDto.getPostponeCount();
                                                        final MaxPostponeCount maxPostponeCount =
                                                                dueDateDetailDto
                                                                        .getMaxPostponeCount();

                                                        return DueDateDetail
                                                                .reconstructDueDateDetail(
                                                                        dueDate,
                                                                        postponeCount,
                                                                        maxPostponeCount);
                                                    })
                                            .orElse(null);

                            return Task.reconstructTask(
                                    taskId, taskName, status, assignedAccountIds, dueDateDetail);
                        })
                .forEach(tasks::add);

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

    private void deleteTask(final String projectId) {
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
            final String dueDate,
            final int postponeCount,
            final int maxPostponeCount) {
        return;
    }
}
