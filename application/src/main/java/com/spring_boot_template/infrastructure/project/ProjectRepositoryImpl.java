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
import com.spring_boot_template.shared.constants.MessageCode;
import com.spring_boot_template.shared.module.MessageGenerator;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class ProjectRepositoryImpl implements ProjectRepository {
    private final ProjectMapper projectMapper;
    private final MessageGenerator messageGenerator;

    @Override
    public void saveProject(final Project project) {
        final String projectId = project.getProjectId().value();
        final String projectName = project.getProjectName().value();
        projectMapper.insertProject(projectId, projectName);

        projectMapper.deleteProjectAccountParticipations(projectId);
        final Set<AccountId> participatingAccountIds = project.getParticipatingAccountIds();
        participatingAccountIds.forEach(
                projectAccountParticipation ->
                        projectMapper.insertProjectAccountParticipation(
                                projectId, projectAccountParticipation.value()));

        projectMapper.deleteTasks(projectId);
        final List<Task> tasks = new ArrayList<>(project.getTasks());
        saveTasks(projectId, tasks);
    }

    @Override
    public Project findProjectByProjectId(final ProjectId projectId) {
        final ProjectDto projectDto =
                projectMapper
                        .selectProjectByProjectId(projectId.value())
                        .orElseThrow(
                                () -> {
                                    final String message =
                                            messageGenerator.generateMessage(
                                                    MessageCode.NOT_FOUND, Project.class);
                                    return new ResourceNotFoundException(message);
                                });

        final ProjectName projectName = projectDto.projectName();

        final Set<AccountId> participatingAccountIds =
                new HashSet<>(
                        projectMapper.selectProjectAccountParticipationsByProjectId(
                                projectId.value()));

        final LinkedHashSet<Task> tasks = findTasksByProjectId(projectId.value());

        return Project.reconstructProject(projectId, projectName, participatingAccountIds, tasks);
    }

    @Override
    public void deleteProject(final ProjectId projectId) {
        projectMapper.deleteProject(projectId.value());
    }

    private void saveTasks(final String projectId, final List<Task> tasks) {
        tasks.forEach(
                task -> {
                    final String taskId = task.getTaskId().value();
                    final String taskName = task.getTaskName().value();
                    final String status = task.getStatus().toString();
                    final int index = tasks.indexOf(task);
                    projectMapper.insertTask(projectId, taskId, taskName, status, index);

                    final Set<AccountId> assignedAccountIds = task.getAssignedAccountIds();
                    assignedAccountIds.forEach(
                            taskAccountAssignment ->
                                    projectMapper.insertTaskAccountAssignment(
                                            taskId, taskAccountAssignment.value()));

                    task.getDueDateDetail()
                            .ifPresent(dueDateDetail -> saveDueDateDetail(taskId, dueDateDetail));
                });
    }

    private LinkedHashSet<Task> findTasksByProjectId(final String projectId) {
        return projectMapper.selectTasksByProjectId(projectId).stream()
                .map(
                        taskDto -> {
                            final TaskId taskId = taskDto.taskId();
                            final TaskName taskName = taskDto.taskName();
                            final Status status = taskDto.status();

                            final Set<AccountId> assignedAccountIds =
                                    new HashSet<>(
                                            projectMapper.selectTaskAccountAssignmentsByTaskId(
                                                    taskId.value()));

                            final DueDateDetail dueDateDetail =
                                    findDueDateDetailByTaskId(taskId.value()).orElse(null);

                            return Task.reconstructTask(
                                    taskId, taskName, status, assignedAccountIds, dueDateDetail);
                        })
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private void saveDueDateDetail(final String taskId, final DueDateDetail dueDateDetail) {
        final LocalDateTime dueDate = dueDateDetail.getDueDate().value();
        final int postponeCount = dueDateDetail.getPostponeCount().value();
        final int maxPostponeCount = dueDateDetail.getMaxPostponeCount().value();
        projectMapper.insertDueDateDetail(taskId, dueDate, postponeCount, maxPostponeCount);
    }

    private Optional<DueDateDetail> findDueDateDetailByTaskId(final String taskId) {
        return projectMapper
                .selectDueDateDetailByTaskId(taskId)
                .map(
                        dueDateDetailDto -> {
                            final DueDate dueDate =
                                    new DueDate(dueDateDetailDto.dueDate().toLocalDateTime());
                            final PostponeCount postponeCount = dueDateDetailDto.postponeCount();
                            final MaxPostponeCount maxPostponeCount =
                                    dueDateDetailDto.maxPostponeCount();
                            return DueDateDetail.reconstructDueDateDetail(
                                    dueDate, postponeCount, maxPostponeCount);
                        });
    }
}
