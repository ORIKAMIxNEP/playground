package com.spring_boot_template.domain.model.task.factory;

import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.task.Task;
import com.spring_boot_template.domain.model.task.value.DueDate;
import com.spring_boot_template.domain.model.task.value.MaxPostponeCount;
import com.spring_boot_template.domain.model.task.value.PostponeCount;
import com.spring_boot_template.domain.model.task.value.Status;
import com.spring_boot_template.domain.model.task.value.TaskId;
import com.spring_boot_template.domain.model.task.value.TaskName;
import com.spring_boot_template.domain.shared.IdGenerator;
import java.util.HashSet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public final class TaskFactoryImpl implements TaskFactory {
    @Override
    public Task create(
            final TaskName name, final DueDate dueDate, final MaxPostponeCount maxPostponeCount) {
        final TaskId id = new TaskId(IdGenerator.generate());
        final Status status = Status.UNDONE;
        final HashSet<AccountId> assignedAccountIds = new HashSet<>();
        final PostponeCount postponeCount = new PostponeCount(0);

        return new Task(
                id, name, status, assignedAccountIds, dueDate, postponeCount, maxPostponeCount);
    }
}
