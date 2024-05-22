package com.spring_boot_template.application.record;

import com.spring_boot_template.presentation.record.response.FetchRecordResponse;

public interface FetchRecordUseCase {
  FetchRecordResponse execute(final long recordId);
}
