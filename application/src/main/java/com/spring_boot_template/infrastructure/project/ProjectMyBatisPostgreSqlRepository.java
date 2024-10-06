package com.spring_boot_template.infrastructure.project;

import com.spring_boot_template.domain.exception.ResourceNotFoundException;
import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.due_date_detail.DueDateDetail;
import com.spring_boot_template.domain.model.due_date_detail.value.DueDate;
import com.spring_boot_template.domain.model.due_date_detail.value.MaxPostponeCount;
import com.spring_boot_template.domain.model.due_date_detail.value.PostponeCount;
import com.spring_boot_template.domain.model.project.Project;
import com.spring_boot_template.domain.model.project.ProjectRepository;
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
import java.util.Set;
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
                    final int index = tasks.indexOf(task);
                    taskMapper.insertTask(projectId, task, index);

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
        final ProjectDto projectDto = projectMapper.selectProjectByProjectId(projectId);

        if (Objects.isNull(projectDto)) {
            throw new ResourceNotFoundException("Project is not found");
        }

        final ProjectName projectName = projectDto.getProjectName();
        final Set<AccountId> participatingAccountIds = projectDto.getParticipatingAccountIds();

        final List<TaskDto> taskDtos = taskMapper.selectTasksByProjectId(projectId);
        final List<DueDateDetailDto> dueDateDetailDtos =
                dueDateDetailMapper.selectDueDateDetailsByProjectId(projectId);

        final ListOrderedSet<Task> tasks = new ListOrderedSet<>();
        taskDtos.stream()
                .map(
                        taskDto -> {
                            final TaskId taskId = taskDto.getTaskId();
                            final TaskName taskName = taskDto.getTaskName();
                            final Status status = taskDto.getStatus();
                            final Set<AccountId> assignedAccountIds =
                                    taskDto.getAssignedAccountIds();
                            final DueDateDetail dueDateDetail =
                                    dueDateDetailDtos.stream()
                                            .filter(
                                                    dueDateDetailDto ->
                                                            dueDateDetailDto
                                                                    .getTaskId()
                                                                    .getValue()
                                                                    .equals(taskId.getValue()))
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
        projectMapper.deleteProject(projectId);
    }
}
