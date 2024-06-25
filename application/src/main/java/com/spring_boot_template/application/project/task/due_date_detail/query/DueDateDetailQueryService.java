package com.spring_boot_template.application.project.task.due_date_detail.query;

import com.spring_boot_template.application.project.task.due_date_detail.query.dto.DueDateDetailQueryDto;
import com.spring_boot_template.domain.model.project.task.value.TaskId;
import java.util.Optional;

public interface DueDateDetailQueryService {
    Optional<DueDateDetailQueryDto> findDueDateDetailByTaskId(final TaskId taskId);
}
