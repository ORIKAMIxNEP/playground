package com.spring_boot_template.application.task.query;

import java.util.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public final class FetchTaskQueryDto {
    private final String taskName;
    private final String status;
    private final String assignedAccountId;
    private final String dueDate;
    private final int postponeCount;
    private final int maxPostponeCount;

    public Optional<String> getDueDate() {
        return Optional.ofNullable(dueDate);
    }
}
