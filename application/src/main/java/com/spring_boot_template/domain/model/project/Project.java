package com.spring_boot_template.domain.model.project;

import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.project.value.ProjectId;
import com.spring_boot_template.domain.model.project.value.ProjectName;
import com.spring_boot_template.domain.model.task.Task;
import com.spring_boot_template.domain.model.task.factory.TaskFactory;
import com.spring_boot_template.domain.model.task.value.DueDate;
import com.spring_boot_template.domain.model.task.value.MaxPostponeCount;
import com.spring_boot_template.domain.model.task.value.TaskId;
import com.spring_boot_template.domain.model.task.value.TaskName;
import jakarta.validation.ValidationException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public final class Project {
    private final ProjectId id;
    private ProjectName name;
    private final HashSet<AccountId> participatedAccountIds;
    private final LinkedHashSet<Task> tasks;

    private final int ASSIGNABLE_TASK_COUNT_FOR_ACCOUNT = 10;

    public void createTask(
            final TaskFactory taskFactory,
            final TaskName taskName,
            final DueDate dueDate,
            final MaxPostponeCount maxPostponeCount) {
        tasks.add(taskFactory.create(taskName, dueDate, maxPostponeCount));
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
