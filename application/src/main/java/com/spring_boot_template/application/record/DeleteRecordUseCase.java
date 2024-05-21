package com.spring_boot_template.application.record;

import com.spring_boot_template.presentation.record.request.DeleteRecordRequest;
import com.spring_boot_template.presentation.record.response.DeleteRecordResponse;

public interface DeleteRecordUseCase {
  // レコード削除
  DeleteRecordResponse deleteRecord(final DeleteRecordRequest deleteRecordRequest);
}
