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
  AddRecordOutput add(final AddRecordInput addRecordInput);

  // レコード取得
  FetchRecordOutput fetch();

  // レコード更新
  UpdateRecordOutput update(final UpdateRecordInput updateRecordInput);

  // レコードカラム1更新
  UpdateRecordColumn1Output updateColumn1(final UpdateRecordColumn1Input updateRecordColumn1Input);

  // レコード削除
  DeleteRecordOutput delete(final DeleteRecordInput deleteRecordInput);
}
