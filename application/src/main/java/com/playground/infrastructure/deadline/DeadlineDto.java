package com.playground.infrastructure.deadline;

import com.playground.domain.model.deadline.value.MaxPostponeCount;
import com.playground.domain.model.deadline.value.PostponeCount;
import java.sql.Timestamp;

public record DeadlineDto(
    Timestamp dueDate, PostponeCount postponeCount, MaxPostponeCount maxPostponeCount) {}
