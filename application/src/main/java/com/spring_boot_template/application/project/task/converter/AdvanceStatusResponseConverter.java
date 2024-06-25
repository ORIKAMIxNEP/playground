package com.spring_boot_template.application.project.task.converter;

import com.spring_boot_template.domain.model.project.task.value.Status;
import com.spring_boot_template.presentation.controller.project.task.response.AdvanceStatusResponse;
import org.springframework.stereotype.Service;

@Service
public final class AdvanceStatusResponseConverter {
    public AdvanceStatusResponse execute(final Status status) {
        final String statusResponse = status.toString();

        return new AdvanceStatusResponse(statusResponse);
    }
}
