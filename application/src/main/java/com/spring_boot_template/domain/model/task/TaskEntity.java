package com.spring_boot_template.domain.model.task;

import com.spring_boot_template.domain.exception.DomainException;
import com.spring_boot_template.domain.model.task.value.DueDateValue;
import com.spring_boot_template.domain.model.task.value.MaxPostponeCountValue;
import com.spring_boot_template.domain.model.task.value.PostponeCountValue;
import com.spring_boot_template.domain.model.task.value.TaskIdValue;
import com.spring_boot_template.domain.model.task.value.TaskNameValue;
import com.spring_boot_template.domain.model.task.value.TaskStatusValue;
import com.spring_boot_template.domain.model.user.value.UserIdValue;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Getter
public class TaskEntity {
    private final TaskIdValue taskId;
    private final TaskNameValue taskName;
    private TaskStatusValue taskStatus;
    private final UserIdValue assignedUserId;
    private DueDateValue dueDate;
    private PostponeCountValue postponeCount;
    private MaxPostponeCountValue maxPostponeCount;

    public final void updateTaskStatus() {
        taskStatus =
                Optional.ofNullable(taskStatus.nextTaskStatus())
                        .orElseThrow(
                                () -> new DomainException("Cannot update TaskStatus any more"));
    }
}
