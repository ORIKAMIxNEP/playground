package com.spring_boot_template.application.record;

import com.spring_boot_template.presentation.record.request.FetchRecordRequest;
import com.spring_boot_template.presentation.record.response.FetchRecordResponse;

public interface FetchRecordUseCase {
  // レコード取得
  FetchRecordResponse execute(final FetchRecordRequest fetchRecordRequest);
}
