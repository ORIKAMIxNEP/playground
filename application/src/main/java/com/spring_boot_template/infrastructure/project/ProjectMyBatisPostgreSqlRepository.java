package com.spring_boot_template.infrastructure.project;

import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.project.Project;
import com.spring_boot_template.domain.model.project.ProjectRepository;
import com.spring_boot_template.domain.model.project.task.Task;
import com.spring_boot_template.domain.model.project.task.due_date_detail.DueDateDetail;
import com.spring_boot_template.domain.model.project.task.due_date_detail.value.DueDate;
import com.spring_boot_template.domain.model.project.task.value.Status;
import com.spring_boot_template.domain.model.project.task.value.TaskId;
import com.spring_boot_template.domain.model.project.task.value.TaskName;
import com.spring_boot_template.domain.model.project.value.ProjectId;
import com.spring_boot_template.domain.model.project.value.ProjectName;
import com.spring_boot_template.infrastructure.project.dto.DueDateDetailDto;
import com.spring_boot_template.infrastructure.project.dto.ProjectDto;
import com.spring_boot_template.infrastructure.project.dto.TaskDto;
import java.util.ArrayList;
import java.util.HashSet;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.set.ListOrderedSet;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProjectMyBatisPostgreSqlRepository implements ProjectRepository {
    private final ProjectMapper projectMapper;

    @Override
    public void saveProject(final Project project) {
        projectMapper.upsertProject(project);

        final ProjectId projectId = project.getProjectId();

        projectMapper.deleteProjectParticipatingAccount(projectId);
        project.getParticipatingAccountIds()
                .forEach(
                        participatingAccountId ->
                                projectMapper.insertProjectParticipatingAccount(
                                        projectId, participatingAccountId));

        projectMapper.deleteTask(projectId);

        final ListOrderedSet<Task> tasks = project.getTasks();

        tasks.forEach(
                task -> {
                    projectMapper.insertTask(projectId, task, tasks.indexOf(task));

                    final TaskId taskId = task.getTaskId();

                    task.getAssignedAccountIds()
                            .forEach(
                                    assignedAccountId ->
                                            projectMapper.insertTaskAssignedAccount(
                                                    taskId, assignedAccountId));
                    projectMapper.insertDueDateDetail(taskId, task.getDueDateDetail());
                });
    }

    @Override
    public Project findProjectByProjectId(final ProjectId projectId) {
        final ProjectDto projectDto = projectMapper.selectProjectByProjectId(projectId);
        final ProjectName projectName = projectDto.getProjectName();
        final HashSet<AccountId> participatingAccountIds =
                new HashSet<>(projectDto.getParticipatingAccountIds());
        final ArrayList<TaskDto> taskDtos = projectMapper.selectTasksByProjectId(projectId);
        final ArrayList<DueDateDetailDto> dueDateDetailDtos =
                projectMapper.selectDueDateDetailsByProjectId(projectId);
        final ListOrderedSet<Task> tasks = new ListOrderedSet<>();

        taskDtos.stream()
                .map(
                        taskDto -> {
                            final TaskId taskId = taskDto.getTaskId();
                            final TaskName taskName = taskDto.getTaskName();
                            final Status status = taskDto.getStatus();
                            final HashSet<AccountId> assignedAccountIds =
                                    new HashSet<>(taskDto.getAssignedAccountIds());
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
                                                    dueDateDetailDto ->
                                                            DueDateDetail.reconstructDueDateDetail(
                                                                    new DueDate(
                                                                            dueDateDetailDto
                                                                                    .getDueDate()
                                                                                    .toLocalDateTime()),
                                                                    dueDateDetailDto
                                                                            .getPostponeCount(),
                                                                    dueDateDetailDto
                                                                            .getMaxPostponeCount()))
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
