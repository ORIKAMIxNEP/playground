package com.spring_boot_template.application.project.task.due_date_detail.query.dto;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(force = true)
@Getter
public final class DueDateQueryDto {
    private final Timestamp value;
}
