package com.spring_boot_template.infrastructure.project.task.due_date_detail.dto;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(force = true)
@Getter
public final class DueDateDto {
    private final Timestamp value;
}
