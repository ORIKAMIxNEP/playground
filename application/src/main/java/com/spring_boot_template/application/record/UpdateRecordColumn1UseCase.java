package com.spring_boot_template.application.record;

import com.spring_boot_template.presentation.record.request.UpdateRecordColumn1Request;
import com.spring_boot_template.presentation.record.response.UpdateRecordColumn1Response;

public interface UpdateRecordColumn1UseCase {
  // レコードカラム1更新
  UpdateRecordColumn1Response updateRecordColumn1(
      final UpdateRecordColumn1Request updateRecordColumn1Request);
}
