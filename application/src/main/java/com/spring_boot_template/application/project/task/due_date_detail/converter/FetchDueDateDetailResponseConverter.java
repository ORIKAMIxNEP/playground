package com.spring_boot_template.application.project.task.due_date_detail.converter;

import com.spring_boot_template.application.project.task.due_date_detail.query.dto.DueDateDetailQueryDto;
import com.spring_boot_template.presentation.controller.project.task.due_date_detail.response.FetchDueDateDetailResponse;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public final class FetchDueDateDetailResponseConverter {
    public FetchDueDateDetailResponse execute(
            final Optional<DueDateDetailQueryDto> optionalDueDateDetailQueryDto) {
        return optionalDueDateDetailQueryDto
                .map(
                        dueDateDetailQueryDto -> {
                            final String dueDate = dueDateDetailQueryDto.getDueDate().toString();
                            final int postponeCount =
                                    dueDateDetailQueryDto.getPostponeCount().getValue();
                            final int maxPostponeCount =
                                    dueDateDetailQueryDto.getMaxPostponeCount().getValue();

                            return new FetchDueDateDetailResponse(
                                    dueDate, postponeCount, maxPostponeCount);
                        })
                .orElse(null);
    }
}
