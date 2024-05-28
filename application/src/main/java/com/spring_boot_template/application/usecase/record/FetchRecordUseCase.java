package com.spring_boot_template.application.usecase.record;

import com.spring_boot_template.presentation.controller.record.response.FetchRecordResponse;

public interface FetchRecordUseCase {
    FetchRecordResponse execute(final long recordId);
}
