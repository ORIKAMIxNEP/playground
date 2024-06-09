package com.spring_boot_template.domain.model.task;

import com.spring_boot_template.domain.exception.DomainException;
import com.spring_boot_template.domain.model.task.value.DueDateValue;
import com.spring_boot_template.domain.model.task.value.MaxPostponeCountValue;
import com.spring_boot_template.domain.model.task.value.PostponeCountValue;
import com.spring_boot_template.domain.model.task.value.TaskIdValue;
import com.spring_boot_template.domain.model.task.value.TaskNameValue;
import com.spring_boot_template.domain.model.task.value.TaskStatusType;
import com.spring_boot_template.domain.model.user.value.UserIdValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor(force = true)
@AllArgsConstructor
@Getter
public final class TaskEntity {
    private final TaskIdValue taskId;
    private final TaskNameValue taskName;
    private TaskStatusType taskStatus;
    private final UserIdValue assignedUserId;
    private DueDateValue dueDate;
    private PostponeCountValue postponeCount;
    private MaxPostponeCountValue maxPostponeCount;

    public void updateTaskStatus() {
        taskStatus =
                Optional.ofNullable(taskStatus.nextTaskStatus())
                        .orElseThrow(
                                () -> new DomainException("Cannot update TaskStatusType any more"));
    }
}
