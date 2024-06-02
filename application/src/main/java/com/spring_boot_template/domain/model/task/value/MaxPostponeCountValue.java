package com.spring_boot_template.domain.model.task.value;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MaxPostponeCountValue {
    @Getter
    @Min(0)
    @Max(10)
    private final int value;
}
