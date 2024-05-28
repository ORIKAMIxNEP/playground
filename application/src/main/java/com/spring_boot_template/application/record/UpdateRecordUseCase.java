package com.spring_boot_template.application.usecase.record;

import com.spring_boot_template.presentation.controller.record.request.UpdateRecordRequest;

public interface UpdateRecordUseCase {
    void execute(final UpdateRecordRequest updateRecordRequest);
}
