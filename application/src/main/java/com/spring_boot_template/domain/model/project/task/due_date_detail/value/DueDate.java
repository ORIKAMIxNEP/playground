package com.spring_boot_template.domain.model.project.task.due_date_detail.value;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class DueDate {
    @Getter private final LocalDateTime value;
}
