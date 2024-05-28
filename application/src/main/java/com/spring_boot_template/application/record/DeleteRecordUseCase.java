package com.spring_boot_template.application.usecase.record;

import com.spring_boot_template.presentation.controller.record.request.DeleteRecordRequest;

public interface DeleteRecordUseCase {
    void execute(final DeleteRecordRequest deleteRecordRequest);
}
