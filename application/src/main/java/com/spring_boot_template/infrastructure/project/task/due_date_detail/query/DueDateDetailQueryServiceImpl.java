package com.spring_boot_template.infrastructure.project.task.due_date_detail.query;

import com.spring_boot_template.application.project.task.due_date_detail.query.DueDateDetailQueryService;
import com.spring_boot_template.application.project.task.due_date_detail.query.dto.DueDateDetailQueryDto;
import com.spring_boot_template.domain.model.project.task.value.TaskId;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
final class DueDateDetailQueryServiceImpl implements DueDateDetailQueryService {
    private final DueDateDetailQueryMapper dueDateDetailQueryMapper;

    @Override
    public Optional<DueDateDetailQueryDto> findDueDateDetailByTaskId(final TaskId taskId) {
        return Optional.ofNullable(dueDateDetailQueryMapper.selectDueDateDetailByTaskId(taskId));
    }
}
