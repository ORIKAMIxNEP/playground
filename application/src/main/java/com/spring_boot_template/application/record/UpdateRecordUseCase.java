package com.spring_boot_template.application.record;

import com.spring_boot_template.presentation.record.request.UpdateRecordRequest;
import com.spring_boot_template.presentation.record.response.UpdateRecordResponse;

public interface UpdateRecordUseCase {
  UpdateRecordResponse execute(final UpdateRecordRequest updateRecordRequest);
}
