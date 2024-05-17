package jp.spring_boot_template.application.usecase.record;

import jp.spring_boot_template.application.dto.record.AddInput;
import jp.spring_boot_template.application.dto.record.AddOutput;
import jp.spring_boot_template.application.dto.record.DeleteInput;
import jp.spring_boot_template.application.dto.record.DeleteOutput;
import jp.spring_boot_template.application.dto.record.FetchOutput;
import jp.spring_boot_template.application.dto.record.UpdateColumn1Input;
import jp.spring_boot_template.application.dto.record.UpdateColumn1Output;
import jp.spring_boot_template.application.dto.record.UpdateInput;
import jp.spring_boot_template.application.dto.record.UpdateOutput;

public interface RecordUseCase {
  // レコード追加
  AddOutput addRecord(final AddInput addInput);

  // レコード取得
  FetchOutput fetchRecord();

  // レコード更新
  UpdateOutput updateRecord(final UpdateInput updateInput);

  // レコードカラム1更新
  UpdateColumn1Output updateRecordColumn1(final UpdateColumn1Input updateColumn1Input);

  // レコード削除
  DeleteOutput deleteRecord(final DeleteInput deleteInput);
}
