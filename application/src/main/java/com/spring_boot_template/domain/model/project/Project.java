package com.spring_boot_template.domain.model.project;

import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.project.task.Task;
import com.spring_boot_template.domain.model.project.task.value.TaskId;
import com.spring_boot_template.domain.model.project.task.value.TaskName;
import com.spring_boot_template.domain.model.project.value.ProjectId;
import com.spring_boot_template.domain.model.project.value.ProjectName;
import com.spring_boot_template.domain.shared.IdGenerator;
import jakarta.validation.ValidationException;
import java.util.HashSet;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.set.ListOrderedSet;

@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public final class Project {
    private final ProjectId id;
    private ProjectName name;
    private final HashSet<AccountId> participatingAccountIds;
    private final ListOrderedSet<Task> tasks;

    private final int ASSIGNABLE_TASK_COUNT_FOR_ACCOUNT = 10;

    public static Project create(final ProjectName name) {
        final ProjectId id = new ProjectId(IdGenerator.generate());
        final HashSet<AccountId> participatingAccountIds = new HashSet<>();
        final ListOrderedSet<Task> tasks = new ListOrderedSet<>();

        return new Project(id, name, participatingAccountIds, tasks);
    }

    public static Project reconstruct(
            final ProjectId id,
            final ProjectName name,
            final HashSet<AccountId> participatingAccountIds,
            final ListOrderedSet<Task> tasks) {
        return new Project(id, name, participatingAccountIds, tasks);
    }

    public void createTask(final TaskName taskName) {
        tasks.add(Task.create(taskName));
    }

    public Task findTaskByTaskId(final TaskId taskId) {
        return tasks.stream()
                .filter(task -> task.getId().equals(taskId))
                .findFirst()
                .orElseThrow(() -> new ValidationException("Task is not found"));
    }

    public void advanceTaskStatus(final TaskId taskId) {
        final Task task = findTaskByTaskId(taskId);

        task.advanceStatus();
    }

    public void assignAccountToTask(final TaskId taskId, final AccountId assignedAccountId) {
        final long assignedTaskCountToAccount =
                tasks.stream()
                        .filter(task -> task.getAssignedAccountIds().contains(assignedAccountId))
                        .filter(task -> task.getStatus().isCountableAsTask())
                        .count();

        // 割り当てられたタスクの数が限界に達している場合
        if (assignedTaskCountToAccount >= ASSIGNABLE_TASK_COUNT_FOR_ACCOUNT) {
            throw new ValidationException("Reached assigned Task count limit");
        }

        final Task task = findTaskByTaskId(taskId);

        task.assignAccount(assignedAccountId);
    }

    public void deleteTask(final TaskId taskId) {
        tasks.removeIf(task -> task.getId().equals(taskId));
    }
}
