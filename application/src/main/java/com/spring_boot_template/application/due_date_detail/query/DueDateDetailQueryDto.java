package com.spring_boot_template.application.due_date_detail.query;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(force = true)
@Getter
public final class DueDateDetailQueryDto {
    private final Timestamp dueDate;
    private final int postponeCount;
    private final int maxPostponeCount;
}
