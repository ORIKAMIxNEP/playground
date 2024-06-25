package com.spring_boot_template.application.project.task.due_date_detail.impl;

import com.spring_boot_template.application.project.task.due_date_detail.FetchDueDateDetailUseCase;
import com.spring_boot_template.application.project.task.due_date_detail.converter.FetchDueDateDetailResponseConverter;
import com.spring_boot_template.application.project.task.due_date_detail.query.DueDateDetailQueryService;
import com.spring_boot_template.application.project.task.due_date_detail.query.dto.DueDateDetailQueryDto;
import com.spring_boot_template.domain.model.project.task.value.TaskId;
import com.spring_boot_template.presentation.controller.project.task.due_date_detail.response.FetchDueDateDetailResponse;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class FetchDueDateDetailUseCaseImpl implements FetchDueDateDetailUseCase {
    private final DueDateDetailQueryService dueDateDetailQueryService;
    private final FetchDueDateDetailResponseConverter fetchDueDateDetailResponseConverter;

    @Override
    @Transactional
    public FetchDueDateDetailResponse execute(final TaskId taskId) {
        final Optional<DueDateDetailQueryDto> optionalDueDateDetailQueryDto =
                dueDateDetailQueryService.findDueDateDetailByTaskId(taskId);

        return fetchDueDateDetailResponseConverter.execute(optionalDueDateDetailQueryDto);
    }
}
