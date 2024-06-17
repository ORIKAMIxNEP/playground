package com.spring_boot_template.domain.model.project.task.due_date_detail;

import com.spring_boot_template.domain.model.project.task.due_date_detail.value.DueDate;
import com.spring_boot_template.domain.model.project.task.due_date_detail.value.MaxPostponeCount;
import com.spring_boot_template.domain.model.project.task.due_date_detail.value.PostponeCount;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class DueDateDetail {
    private DueDate dueDate;
    private PostponeCount postponeCount;
    private final MaxPostponeCount maxPostponeCount;

    public static DueDateDetail create(
            final DueDate dueDate, final MaxPostponeCount maxPostponeCount) {
        final PostponeCount postponeCount = new PostponeCount(0);

        return new DueDateDetail(dueDate, postponeCount, maxPostponeCount);
    }

    public static DueDateDetail reconstruct(
            final DueDate dueDate,
            final PostponeCount postponeCount,
            final MaxPostponeCount maxPostponeCount) {
        return new DueDateDetail(dueDate, postponeCount, maxPostponeCount);
    }
}
