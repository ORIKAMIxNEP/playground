package com.spring_boot_template.application.task.query;

import com.spring_boot_template.application.due_date_detail.query.DueDateDetailQueryDto;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(force = true)
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public final class TaskQueryDto {
    private final String taskName;
    private final String status;
    private final String[] assignedAccountIds;
    @Setter private DueDateDetailQueryDto dueDateDetailQueryDto;

    public Optional<DueDateDetailQueryDto> getDueDateDetailQueryDto() {
        return Optional.ofNullable(dueDateDetailQueryDto);
    }
}
