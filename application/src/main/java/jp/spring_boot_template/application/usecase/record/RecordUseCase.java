package jp.spring_boot_template.application.usecase.record;

import jp.spring_boot_template.application.dto.record.AddRecordInput;
import jp.spring_boot_template.application.dto.record.AddRecordOutput;
import jp.spring_boot_template.application.dto.record.DeleteRecordInput;
import jp.spring_boot_template.application.dto.record.DeleteRecordOutput;
import jp.spring_boot_template.application.dto.record.FetchRecordOutput;
import jp.spring_boot_template.application.dto.record.UpdateRecordColumn1Input;
import jp.spring_boot_template.application.dto.record.UpdateRecordColumn1Output;
import jp.spring_boot_template.application.dto.record.UpdateRecordInput;
import jp.spring_boot_template.application.dto.record.UpdateRecordOutput;

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
