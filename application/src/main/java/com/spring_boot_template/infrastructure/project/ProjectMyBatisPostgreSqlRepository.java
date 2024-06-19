package com.spring_boot_template.infrastructure.project;

import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.project.Project;
import com.spring_boot_template.domain.model.project.ProjectRepository;
import com.spring_boot_template.domain.model.project.task.Task;
import com.spring_boot_template.domain.model.project.task.due_date_detail.DueDateDetail;
import com.spring_boot_template.domain.model.project.task.value.Status;
import com.spring_boot_template.domain.model.project.task.value.TaskId;
import com.spring_boot_template.domain.model.project.task.value.TaskName;
import com.spring_boot_template.domain.model.project.value.ProjectId;
import com.spring_boot_template.domain.model.project.value.ProjectName;
import com.spring_boot_template.infrastructure.project.dto.DueDateDetailDto;
import com.spring_boot_template.infrastructure.project.dto.ProjectDto;
import com.spring_boot_template.infrastructure.project.dto.TaskDto;
import java.util.HashSet;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.set.ListOrderedSet;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProjectMyBatisPostgreSqlRepository implements ProjectRepository {
    private final ProjectMapper projectMapper;

    @Override
    public void saveProject(final Project project) {}

    @Override
    public Project findProjectByProjectId(final ProjectId projectId) {
        final ProjectDto projectDto = projectMapper.selectProjectByProjectId(projectId);
        final ProjectName projectName = projectDto.getProjectName();
        final HashSet<AccountId> participatingAccountIds =
                new HashSet<>(projectMapper.selectParticipatingAccountIdsByProjectId(projectId));
        final List<TaskDto> taskDtos = projectMapper.selectTasksByProjectId(projectId);
        final List<DueDateDetailDto> dueDateDetailDtos =
                projectMapper.selectDueDateDetailsByProjectId(projectId);

        final ListOrderedSet<Task> tasks = new ListOrderedSet<>();
        taskDtos.stream()
                .map(
                        taskDto -> {
                            TaskId taskId = taskDto.getTaskId();
                            TaskName taskName = taskDto.getTaskName();
                            Status status = taskDto.getStatus();
                            HashSet<AccountId> assignedAccountIds =
                                    new HashSet<>(taskDto.getAssignedAccountIds());
                            DueDateDetail dueDateDetail =
                                    dueDateDetailDtos.stream()
                                            .filter(
                                                    dueDateDetailDto ->
                                                            dueDateDetailDto
                                                                    .getTaskId()
                                                                    .equals(taskId))
                                            .findFirst()
                                            .map(
                                                    dueDateDetailDto ->
                                                            DueDateDetail.reconstructDueDateDetail(
                                                                    dueDateDetailDto.getDueDate(),
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
    public void deleteProject(final ProjectId projectId) {}
}
