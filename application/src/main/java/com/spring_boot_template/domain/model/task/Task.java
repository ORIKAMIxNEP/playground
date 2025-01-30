package com.spring_boot_template.domain.model.task;

import com.spring_boot_template.domain.exception.DomainConflictException;
import com.spring_boot_template.domain.exception.DomainKnowledgeException;
import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.due_date_detail.DueDateDetail;
import com.spring_boot_template.domain.model.task.value.Status;
import com.spring_boot_template.domain.model.task.value.TaskId;
import com.spring_boot_template.domain.model.task.value.TaskName;
import com.spring_boot_template.domain.shared.IdGenerator;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.context.MessageSource;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode(of = "taskId")
public final class Task {
    private final TaskId taskId;
    private TaskName taskName;
    private Status status;
    private final Set<AccountId> assignedAccountIds;
    private final DueDateDetail dueDateDetail;

    private final MessageSource messageSource;

    public static Task createTask(final IdGenerator idGenerator, final TaskName taskName) {
        final TaskId taskId = new TaskId(idGenerator.generateId());
        final Status status = Status.UNDONE;
        final Set<AccountId> assignedAccountIds = Collections.emptySet();
        return new Task(taskId, taskName, status, assignedAccountIds, null, null);
    }

    public static Task reconstructTask(
            final TaskId taskId,
            final TaskName taskName,
            final Status status,
            final Set<AccountId> assignedAccountIds,
            final DueDateDetail dueDateDetail,
            final MessageSource messageSource) {
        return new Task(taskId, taskName, status, assignedAccountIds, dueDateDetail, messageSource);
    }

    public void updateTask(final TaskName taskName, final List<AccountId> assignedAccountIds) {
        this.taskName = taskName;

        this.assignedAccountIds.clear();
        assignedAccountIds.forEach(
                assignedAccountId -> {
                    if (!this.assignedAccountIds.add(assignedAccountId)) {
                        final String code = "already-assigned";
                        final Object[] args = new Object[] {"AccountId"};
                        final Locale locale = Locale.getDefault();
                        final String message = messageSource.getMessage(code, args, locale);
                        throw new DomainConflictException(message);
                    }
                });
    }

    public void advanceStatus() {
        status =
                status.nextStatus()
                        .orElseThrow(
                                () -> {
                                    final String code = "status-cannot-advanced";
                                    final Locale locale = Locale.getDefault();
                                    final String message =
                                            messageSource.getMessage(code, null, locale);
                                    return new DomainKnowledgeException(message);
                                });
    }

    public Optional<DueDateDetail> getDueDateDetail() {
        return Optional.ofNullable(dueDateDetail);
    }
}
