package com.spring_boot_template.application.usecase.record;

import com.spring_boot_template.presentation.controller.record.request.AddRecordRequest;

public interface AddRecordUseCase {
    void execute(final AddRecordRequest addRecordRequest);
}
