package com.spring_boot_template.domain.model.project.task.due_date_detail.value;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class MaxPostponeCount {
    @Getter
    @Min(0)
    @Max(10)
    private final int value;
}
