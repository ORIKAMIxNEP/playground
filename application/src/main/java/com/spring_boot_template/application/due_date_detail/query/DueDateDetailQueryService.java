package com.spring_boot_template.application.due_date_detail.query;

import com.spring_boot_template.domain.model.task.value.TaskId;
import java.util.Optional;

public interface DueDateDetailQueryService {
    Optional<DueDateDetailQueryDto> findDueDateDetailByTaskId(final TaskId taskId);
}
