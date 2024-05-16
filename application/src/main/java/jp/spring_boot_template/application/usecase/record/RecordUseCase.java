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
  AddOutput add(final AddInput addInput);

  // レコード取得
  FetchOutput fetch();

  // レコード更新
  UpdateOutput update(final UpdateInput updateInput);

  // レコードカラム1更新
  UpdateColumn1Output updateColumn1(final UpdateColumn1Input updateColumn1Input);

  // レコード削除
  DeleteOutput delete(final DeleteInput deleteInput);
}
