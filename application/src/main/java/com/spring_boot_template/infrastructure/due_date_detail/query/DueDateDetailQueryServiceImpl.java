package com.spring_boot_template.infrastructure.due_date_detail.query;

import com.spring_boot_template.application.due_date_detail.query.DueDateDetailQueryDto;
import com.spring_boot_template.application.due_date_detail.query.DueDateDetailQueryService;
import com.spring_boot_template.domain.model.task.value.TaskId;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
final class DueDateDetailQueryServiceImpl implements DueDateDetailQueryService {
    private final DueDateDetailQueryMapper dueDateDetailQueryMapper;

    @Override
    public Optional<DueDateDetailQueryDto> findDueDateDetailByTaskId(final TaskId taskId) {
        DueDateDetailQueryDto dueDateDetailQueryDto =
                dueDateDetailQueryMapper.selectDueDateDetailByTaskId(taskId);

        return Optional.ofNullable(dueDateDetailQueryDto);
    }
}
