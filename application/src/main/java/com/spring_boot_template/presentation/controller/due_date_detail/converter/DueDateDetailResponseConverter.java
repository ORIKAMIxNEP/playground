package com.spring_boot_template.presentation.controller.due_date_detail.converter;

import com.spring_boot_template.application.due_date_detail.query.DueDateDetailQueryDto;
import com.spring_boot_template.presentation.controller.due_date_detail.response.DueDateDetailResponse;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public final class DueDateDetailResponseConverter {
    public DueDateDetailResponse execute(
            final Optional<DueDateDetailQueryDto> optionalDueDateDetailDto) {
        return optionalDueDateDetailDto
                .map(
                        dueDateDetailDto -> {
                            final String dueDate =
                                    dueDateDetailDto
                                            .getDueDate()
                                            .toString(); // .toLocalDateTime().toString();
                            final int postponeCount = dueDateDetailDto.getPostponeCount();
                            final int maxPostponeCount = dueDateDetailDto.getMaxPostponeCount();

                            return new DueDateDetailResponse(
                                    dueDate, postponeCount, maxPostponeCount);
                        })
                .orElse(null);
    }
}
