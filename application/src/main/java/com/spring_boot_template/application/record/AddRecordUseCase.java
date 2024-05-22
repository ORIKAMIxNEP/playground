package com.spring_boot_template.application.record;

import com.spring_boot_template.presentation.record.request.AddRecordRequest;
import com.spring_boot_template.presentation.record.response.AddRecordResponse;

public interface AddRecordUseCase {
  AddRecordResponse execute(final AddRecordRequest addRecordRequest);
}
