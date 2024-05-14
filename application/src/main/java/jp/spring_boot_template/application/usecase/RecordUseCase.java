package jp.spring_boot_template.application.usecase;

import jp.spring_boot_template.application.dto.AddInput;
import jp.spring_boot_template.application.dto.AddOutput;
import jp.spring_boot_template.application.dto.DeleteInput;
import jp.spring_boot_template.application.dto.DeleteOutput;
import jp.spring_boot_template.application.dto.FetchInput;
import jp.spring_boot_template.application.dto.FetchOutput;
import jp.spring_boot_template.application.dto.UpdateColumn1Input;
import jp.spring_boot_template.application.dto.UpdateColumn1Output;
import jp.spring_boot_template.application.dto.UpdateInput;
import jp.spring_boot_template.application.dto.UpdateOutput;

public interface RecordUseCase {
  // レコード追加
  AddOutput add(final AddInput addInput);

  // レコード取得
  FetchOutput fetch(final FetchInput fetchInput);

  // レコード更新
  UpdateOutput update(final UpdateInput updateInput);

  // レコードカラム1更新
  UpdateColumn1Output updateColumn1(final UpdateColumn1Input updateColumn1Input);

  // レコード削除
  DeleteOutput delete(final DeleteInput deleteInput);
}
