package com.spring_boot_template.domain.model.due_date_detail.value;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record MaxPostponeCount(@Min(0) @Max(10) int value) {}
