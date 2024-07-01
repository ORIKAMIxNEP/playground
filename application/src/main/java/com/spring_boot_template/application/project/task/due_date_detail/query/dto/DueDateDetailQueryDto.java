package com.spring_boot_template.application.project.task.due_date_detail.query.dto;

import com.spring_boot_template.domain.model.project.task.due_date_detail.value.MaxPostponeCount;
import com.spring_boot_template.domain.model.project.task.due_date_detail.value.PostponeCount;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(force = true)
@Getter
public final class DueDateDetailQueryDto {
    private final Timestamp dueDate;
    private final PostponeCount postponeCount;
    private final MaxPostponeCount maxPostponeCount;
}
