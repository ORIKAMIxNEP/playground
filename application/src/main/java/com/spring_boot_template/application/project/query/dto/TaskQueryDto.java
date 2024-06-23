package com.spring_boot_template.application.project.query.dto;

import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.project.task.value.Status;
import com.spring_boot_template.domain.model.project.task.value.TaskName;
import java.util.ArrayList;
import java.util.Optional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(force = true)
@Getter
public final class TaskQueryDto {
    private final TaskName taskName;
    private final Status status;
    private final ArrayList<AccountId> assignedAccountIds;
    @Setter private DueDateDetailQueryDto dueDateDetailQueryDto;

    public Optional<DueDateDetailQueryDto> getDueDateDetailQueryDto() {
        return Optional.ofNullable(dueDateDetailQueryDto);
    }
}
