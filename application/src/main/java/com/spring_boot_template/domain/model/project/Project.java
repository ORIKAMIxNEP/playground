package com.spring_boot_template.domain.model.project;

import com.spring_boot_template.domain.exception.DomainNotFoundException;
import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.project.value.ProjectId;
import com.spring_boot_template.domain.model.project.value.ProjectName;
import com.spring_boot_template.domain.model.task.Task;
import com.spring_boot_template.domain.model.task.value.TaskId;
import com.spring_boot_template.domain.model.task.value.TaskName;
import com.spring_boot_template.domain.shared.IdGenerator;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.MessageSource;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public final class Project {
    private final ProjectId projectId;
    private final ProjectName projectName;
    private final Set<AccountId> participatingAccountIds;
    private final LinkedHashSet<Task> tasks;

    private final MessageSource messageSource;

    public static Project createProject(
            final IdGenerator idGenerator, final ProjectName projectName) {
        final ProjectId projectId = new ProjectId(idGenerator.generateId());
        final Set<AccountId> participatingAccountIds = Collections.emptySet();
        final LinkedHashSet<Task> tasks = new LinkedHashSet<>();
        return new Project(projectId, projectName, participatingAccountIds, tasks, null);
    }

    public static Project reconstructProject(
            final ProjectId projectId,
            final ProjectName projectName,
            final Set<AccountId> participatingAccountIds,
            final LinkedHashSet<Task> tasks,
            final MessageSource messageSource) {
        return new Project(projectId, projectName, participatingAccountIds, tasks, messageSource);
    }

    public TaskId createTask(final IdGenerator idGenerator, final TaskName taskName) {
        final Task task = Task.createTask(idGenerator, taskName);
        tasks.add(task);
        return task.getTaskId();
    }

    public Task findTaskByTaskId(final TaskId taskId) {
        return tasks.stream()
                .filter(task -> task.getTaskId().equals(taskId))
                .findFirst()
                .orElseThrow(
                        () -> {
                            final String code = "not-found";
                            final Object[] args = new Object[] {"Task"};
                            final Locale locale = Locale.getDefault();
                            final String message = messageSource.getMessage(code, args, locale);
                            return new DomainNotFoundException(message);
                        });
    }

    public void updateTask(
            final TaskId taskId,
            final TaskName taskName,
            final List<AccountId> assignedAccountIds) {
        final Task task = findTaskByTaskId(taskId);
        task.updateTask(taskName, assignedAccountIds);
    }

    public void advanceTaskStatus(final TaskId taskId) {
        final Task task = findTaskByTaskId(taskId);
        task.advanceStatus();
    }

    public void deleteTask(final TaskId taskId) {
        final Task task = findTaskByTaskId(taskId);
        tasks.remove(task);
    }
}
