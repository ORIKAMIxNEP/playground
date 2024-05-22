package com.spring_boot_template.application.record;

import com.spring_boot_template.presentation.record.request.DeleteRecordRequest;
import com.spring_boot_template.presentation.record.response.DeleteRecordResponse;

public interface DeleteRecordUseCase {
  DeleteRecordResponse execute(final DeleteRecordRequest deleteRecordRequest);
}
