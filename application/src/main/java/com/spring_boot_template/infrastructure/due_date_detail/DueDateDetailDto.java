package com.spring_boot_template.infrastructure.due_date_detail;

import com.spring_boot_template.domain.model.due_date_detail.value.MaxPostponeCount;
import com.spring_boot_template.domain.model.due_date_detail.value.PostponeCount;
import java.sql.Timestamp;

public record DueDateDetailDto(
        Timestamp dueDate, PostponeCount postponeCount, MaxPostponeCount maxPostponeCount) {}
