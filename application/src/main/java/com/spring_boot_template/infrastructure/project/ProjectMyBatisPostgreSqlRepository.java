package com.spring_boot_template.infrastructure.project;

import com.spring_boot_template.domain.exception.ValidationException;
import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.project.Project;
import com.spring_boot_template.domain.model.project.ProjectRepository;
import com.spring_boot_template.domain.model.project.task.Task;
import com.spring_boot_template.domain.model.project.task.due_date_detail.DueDateDetail;
import com.spring_boot_template.domain.model.project.task.due_date_detail.value.DueDate;
import com.spring_boot_template.domain.model.project.task.due_date_detail.value.MaxPostponeCount;
import com.spring_boot_template.domain.model.project.task.due_date_detail.value.PostponeCount;
import com.spring_boot_template.domain.model.project.task.value.Status;
import com.spring_boot_template.domain.model.project.task.value.TaskId;
import com.spring_boot_template.domain.model.project.task.value.TaskName;
import com.spring_boot_template.domain.model.project.value.ProjectId;
import com.spring_boot_template.domain.model.project.value.ProjectName;
import com.spring_boot_template.infrastructure.project.dto.ProjectDto;
import com.spring_boot_template.infrastructure.project.task.TaskMapper;
import com.spring_boot_template.infrastructure.project.task.dto.TaskDto;
import com.spring_boot_template.infrastructure.project.task.due_date_detail.DueDateDetailMapper;
import com.spring_boot_template.infrastructure.project.task.due_date_detail.dto.DueDateDetailDto;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.set.ListOrderedSet;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class ProjectMyBatisPostgreSqlRepository implements ProjectRepository {
    private final ProjectMapper projectMapper;
    private final TaskMapper taskMapper;
    private final DueDateDetailMapper dueDateDetailMapper;

    @Override
    public void saveProject(final Project project) {
        projectMapper.upsertProject(project);

        final ProjectId projectId = project.getProjectId();

        projectMapper.deleteParticipatingAccounts(projectId);
        project.getParticipatingAccountIds()
                .forEach(
                        participatingAccountId ->
                                projectMapper.insertParticipatingAccount(
                                        projectId, participatingAccountId));

        taskMapper.deleteTasks(projectId);
        final ListOrderedSet<Task> tasks = project.getTasks();
        tasks.forEach(
                task -> {
                    taskMapper.insertTask(projectId, task, tasks.indexOf(task));

                    final TaskId taskId = task.getTaskId();

                    task.getAssignedAccountIds()
                            .forEach(
                                    assignedAccountId ->
                                            taskMapper.insertAssignedAccount(
                                                    taskId, assignedAccountId));
                    task.getDueDateDetail()
                            .ifPresent(
                                    dueDateDetail ->
                                            dueDateDetailMapper.insertDueDateDetail(
                                                    taskId, dueDateDetail));
                });
    }

    @Override
    public Project findProjectByProjectId(final ProjectId projectId) {
        final Optional<ProjectDto> optionalProjectDto =
                Optional.ofNullable(projectMapper.selectProjectByProjectId(projectId));
        final ProjectDto projectDto =
                optionalProjectDto.orElseThrow(
                        () -> new ValidationException("Project is not found"));
        final ProjectName projectName = projectDto.getProjectName();
        final HashSet<AccountId> participatingAccountIds =
                new HashSet<>(projectDto.getParticipatingAccountIds());
        final Optional<ArrayList<TaskDto>> optionalTaskDtos =
                Optional.ofNullable(taskMapper.selectTasksByProjectId(projectId));
        final Optional<ArrayList<DueDateDetailDto>> optionalDueDateDetailDtos =
                Optional.ofNullable(dueDateDetailMapper.selectDueDateDetailsByProjectId(projectId));
        final ListOrderedSet<Task> tasks = new ListOrderedSet<>();

        optionalTaskDtos.ifPresent(
                taskDtos ->
                        taskDtos.stream()
                                .map(
                                        taskDto -> {
                                            final TaskId taskId = taskDto.getTaskId();
                                            final TaskName taskName = taskDto.getTaskName();
                                            final Status status = taskDto.getStatus();
                                            final HashSet<AccountId> assignedAccountIds =
                                                    taskDto.getAssignedAccountIds()
                                                            .map(HashSet::new)
                                                            .orElseGet(HashSet::new);
                                            final DueDateDetail dueDateDetail =
                                                    optionalDueDateDetailDtos
                                                            .flatMap(
                                                                    dueDateDetailDtos ->
                                                                            dueDateDetailDtos
                                                                                    .stream()
                                                                                    .filter(
                                                                                            dueDateDetailDto ->
                                                                                                    dueDateDetailDto
                                                                                                            .getTaskId()
                                                                                                            .getValue()
                                                                                                            .equals(
                                                                                                                    taskId
                                                                                                                            .getValue()))
                                                                                    .findFirst()
                                                                                    .map(
                                                                                            dueDateDetailDto -> {
                                                                                                final
                                                                                                DueDate
                                                                                                        dueDate =
                                                                                                                new DueDate(
                                                                                                                        dueDateDetailDto
                                                                                                                                .getDueDate()
                                                                                                                                .toLocalDateTime());
                                                                                                final
                                                                                                PostponeCount
                                                                                                        postponeCount =
                                                                                                                dueDateDetailDto
                                                                                                                        .getPostponeCount();
                                                                                                final
                                                                                                MaxPostponeCount
                                                                                                        maxPostponeCount =
                                                                                                                dueDateDetailDto
                                                                                                                        .getMaxPostponeCount();

                                                                                                return DueDateDetail
                                                                                                        .reconstructDueDateDetail(
                                                                                                                dueDate,
                                                                                                                postponeCount,
                                                                                                                maxPostponeCount);
                                                                                            }))
                                                            .orElse(null);

                                            return Task.reconstructTask(
                                                    taskId,
                                                    taskName,
                                                    status,
                                                    assignedAccountIds,
                                                    dueDateDetail);
                                        })
                                .forEach(tasks::add));

        return Project.reconstructProject(projectId, projectName, participatingAccountIds, tasks);
    }

    @Override
    public void deleteProject(final ProjectId projectId) {
        projectMapper.deleteProject(projectId);
    }
}
