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
    public void save(final Project project) {}

    @Override
    public Project findById(final ProjectId id) {
        final ProjectDto projectDto = projectMapper.selectById(id);
        final ProjectName projectName = projectDto.name();
        final HashSet<AccountId> participatingAccountIds = projectDto.participatingAccountIds();
        final List<TaskDto> taskDtos = projectMapper.selectTasksByProjectId(id);
        final List<DueDateDetailDto> dueDateDetailDtos =
                projectMapper.selectDueDateDetailsByProjectId(id);

        final ListOrderedSet<Task> tasks = new ListOrderedSet<>();
        taskDtos.stream()
                .map(
                        taskDto -> {
                            TaskId taskId = taskDto.id();
                            TaskName taskName = taskDto.name();
                            Status status = taskDto.status();
                            HashSet<AccountId> assignedAccountIds = taskDto.assignedAccountIds();

                            DueDateDetail dueDateDetail =
                                    dueDateDetailDtos.stream()
                                            .filter(
                                                    dueDateDetailDto ->
                                                            dueDateDetailDto
                                                                    .taskId()
                                                                    .equals(taskId))
                                            .findFirst()
                                            .map(
                                                    dueDateDetailDto ->
                                                            DueDateDetail.reconstruct(
                                                                    dueDateDetailDto.dueDate(),
                                                                    dueDateDetailDto
                                                                            .postponeCount(),
                                                                    dueDateDetailDto
                                                                            .maxPostponeCount()))
                                            .orElse(null);

                            return Task.reconstruct(
                                    taskId, taskName, status, assignedAccountIds, dueDateDetail);
                        })
                .forEach(tasks::add);

        return Project.reconstruct(id, projectName, participatingAccountIds, tasks);
    }

    @Override
    public void delete(final ProjectId id) {}
}
