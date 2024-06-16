package com.spring_boot_template.domain.model.project.task.due_date;

import com.spring_boot_template.domain.model.project.task.due_date.value.Date;
import com.spring_boot_template.domain.model.project.task.due_date.value.MaxPostponeCount;
import com.spring_boot_template.domain.model.project.task.due_date.value.PostponeCount;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class DueDate {
    private Date date;
    private PostponeCount postponeCount;
    private final MaxPostponeCount maxPostponeCount;

    public static DueDate create(final Date date, final MaxPostponeCount maxPostponeCount) {
        final PostponeCount postponeCount = new PostponeCount(0);

        return new DueDate(date, postponeCount, maxPostponeCount);
    }

    public static DueDate reconstruct(
            final Date date,
            final PostponeCount postponeCount,
            final MaxPostponeCount maxPostponeCount) {
        return new DueDate(date, postponeCount, maxPostponeCount);
    }
}
