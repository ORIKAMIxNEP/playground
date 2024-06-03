package com.spring_boot_template.domain.model.user;

import com.spring_boot_template.domain.model.task.TaskEntity;
import com.spring_boot_template.domain.model.task.factory.TaskFactory;
import com.spring_boot_template.domain.model.task.value.DueDateValue;
import com.spring_boot_template.domain.model.task.value.MaxPostponeCountValue;
import com.spring_boot_template.domain.model.task.value.TaskNameValue;
import com.spring_boot_template.domain.model.task.value.TaskStatusValue;
import com.spring_boot_template.domain.model.user.value.MailAddressValue;
import com.spring_boot_template.domain.model.user.value.PasswordValue;
import com.spring_boot_template.domain.model.user.value.UserIdValue;
import com.spring_boot_template.domain.model.user.value.UserNameValue;
import com.spring_boot_template.domain.model.user.value.UserStatusValue;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Getter
public class UserEntity {
    private final UserIdValue userId;
    private final UserNameValue userName;
    private final PasswordValue password;
    private final List<MailAddressValue> mailAddresses;
    private UserStatusValue userStatus;

    public TaskEntity createTask(
            final TaskFactory taskFactory,
            final TaskNameValue taskName,
            final DueDateValue dueDate,
            final MaxPostponeCountValue maxPostponeCount) {
        return taskFactory.createTask(taskName, userId, dueDate, maxPostponeCount);
    }

    public TaskEntity updateTaskStatus(
            final TaskEntity task, final TaskStatusValue updatedTaskStatus) {
        task.updateTaskStatus(updatedTaskStatus);

        return task;
    }
}
