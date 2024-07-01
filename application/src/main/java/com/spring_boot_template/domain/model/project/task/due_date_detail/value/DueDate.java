package com.spring_boot_template.domain.model.project.task.due_date_detail.value;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public final class DueDate {
    private final LocalDateTime value;
}
