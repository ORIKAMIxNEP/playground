package com.spring_boot_template.domain.model.project.task.due_date_detail.value;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public final class MaxPostponeCount {
    @Min(0)
    @Max(10)
    private final int value;
}
