package com.spring_boot_template.application.project.task;

import com.spring_boot_template.presentation.controller.project.task.response.AdvanceStatusResponse;

public interface AdvanceStatusUseCase {
    AdvanceStatusResponse execute(final String projectIdRequest, final String taskIdRequest);
}
