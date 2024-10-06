package com.spring_boot_template.infrastructure.due_date_detail;

import com.spring_boot_template.domain.model.due_date_detail.value.MaxPostponeCount;
import com.spring_boot_template.domain.model.due_date_detail.value.PostponeCount;
import com.spring_boot_template.domain.model.task.value.TaskId;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(force = true)
@Getter
public final class DueDateDetailDto {
    private final TaskId taskId;
    private final Timestamp dueDate;
    private final PostponeCount postponeCount;
    private final MaxPostponeCount maxPostponeCount;
}
