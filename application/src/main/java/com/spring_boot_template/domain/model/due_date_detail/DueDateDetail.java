package com.spring_boot_template.domain.model.due_date_detail;

import com.spring_boot_template.domain.model.due_date_detail.value.DueDate;
import com.spring_boot_template.domain.model.due_date_detail.value.MaxPostponeCount;
import com.spring_boot_template.domain.model.due_date_detail.value.PostponeCount;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public final class DueDateDetail {
    private DueDate dueDate;
    private PostponeCount postponeCount;
    private final MaxPostponeCount maxPostponeCount;

    public static DueDateDetail createDueDateDetail(
            final DueDate dueDate, final MaxPostponeCount maxPostponeCount) {
        final PostponeCount postponeCount = new PostponeCount(0);
        return new DueDateDetail(dueDate, postponeCount, maxPostponeCount);
    }

    public static DueDateDetail reconstructDueDateDetail(
            final DueDate dueDate,
            final PostponeCount postponeCount,
            final MaxPostponeCount maxPostponeCount) {
        return new DueDateDetail(dueDate, postponeCount, maxPostponeCount);
    }
}
