package com.spring_boot_template.infrastructure.deadline;

import com.spring_boot_template.domain.model.deadline.value.MaxPostponeCount;
import com.spring_boot_template.domain.model.deadline.value.PostponeCount;
import java.sql.Timestamp;

public record DeadlineDto(
        Timestamp dueDate, PostponeCount postponeCount, MaxPostponeCount maxPostponeCount) {}
