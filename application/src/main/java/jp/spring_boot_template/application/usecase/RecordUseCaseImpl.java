package jp.spring_boot_template.application.usecase;

import java.util.Objects;
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
import jp.spring_boot_template.domain.model.entity.Records;
import jp.spring_boot_template.infrastructure.repository.RecordsRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RecordUseCaseImpl implements RecordUseCase {
  private final RecordsRepositoryImpl recordsRepositoryImpl;

  // レコード追加
  public AddOutput add(final AddInput addInput) {
    recordsRepositoryImpl.add(addInput.column1(), addInput.column2());

    return AddOutput.builder().success(true).build();
  }

  // レコード取得
  public FetchOutput fetch(final FetchInput fetchInput) {
    final Records records = recordsRepositoryImpl.fetch(fetchInput.recordId());

    // レコードが存在しない場合
    if (Objects.isNull(records)) {
      return FetchOutput.builder().success(false).column1(null).column2(null).build();
    }

    return FetchOutput.builder()
        .success(true)
        .column1(records.getColumn1())
        .column2(records.getColumn2())
        .build();
  }

  // レコード更新
  public UpdateOutput update(final UpdateInput updateInput) {
    final long recordId = updateInput.recordId();

    // レコードが存在しない場合
    if (Objects.isNull(recordsRepositoryImpl.fetch(recordId))) {
      return UpdateOutput.builder().success(false).build();
    }

    recordsRepositoryImpl.update(recordId, updateInput.column1(), updateInput.column2());

    return UpdateOutput.builder().success(true).build();
  }

  // レコードカラム1更新
  public UpdateColumn1Output updateColumn1(final UpdateColumn1Input updateColumn1Input) {
    final long recordId = updateColumn1Input.recordId();

    // レコードが存在しない場合
    if (Objects.isNull(recordsRepositoryImpl.fetch(recordId))) {
      return UpdateColumn1Output.builder().success(false).build();
    }

    recordsRepositoryImpl.updateColumn1(recordId, updateColumn1Input.column1());

    return UpdateColumn1Output.builder().success(true).build();
  }

  // レコード削除
  public DeleteOutput delete(final DeleteInput deleteInput) {
    final long recordId = deleteInput.recordId();

    // レコードが存在しない場合
    if (Objects.isNull(recordsRepositoryImpl.fetch(recordId))) {
      return DeleteOutput.builder().success(false).build();
    }

    recordsRepositoryImpl.delete(recordId);

    return DeleteOutput.builder().success(true).build();
  }
}
