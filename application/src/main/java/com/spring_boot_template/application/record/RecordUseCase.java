package com.spring_boot_template.application.record;

import com.spring_boot_template.application.dto.record.AddRecordInput;
import com.spring_boot_template.application.dto.record.AddRecordOutput;
import com.spring_boot_template.application.dto.record.DeleteRecordInput;
import com.spring_boot_template.application.dto.record.DeleteRecordOutput;
import com.spring_boot_template.application.dto.record.FetchRecordOutput;
import com.spring_boot_template.application.dto.record.UpdateRecordColumn1Input;
import com.spring_boot_template.application.dto.record.UpdateRecordColumn1Output;
import com.spring_boot_template.application.dto.record.UpdateRecordInput;
import com.spring_boot_template.application.dto.record.UpdateRecordOutput;

public interface RecordUseCase {
  // レコード追加
  AddRecordOutput addRecord(final AddRecordInput addRecordInput);

  // レコード取得
  FetchRecordOutput fetchRecord();

  // レコード更新
  UpdateRecordOutput updateRecord(final UpdateRecordInput updateRecordInput);

  // レコードカラム1更新
  UpdateRecordColumn1Output updateRecordColumn1(
      final UpdateRecordColumn1Input updateRecordColumn1Input);

  // レコード削除
  DeleteRecordOutput deleteRecord(final DeleteRecordInput deleteRecordInput);
}
