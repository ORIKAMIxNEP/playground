package jp.spring_boot_template.application.usecase.record;

import java.util.Objects;

import jp.spring_boot_template.application.dto.record.AddInput;
import jp.spring_boot_template.application.dto.record.AddOutput;
import jp.spring_boot_template.application.dto.record.DeleteInput;
import jp.spring_boot_template.application.dto.record.DeleteOutput;
import jp.spring_boot_template.application.dto.record.FetchOutput;
import jp.spring_boot_template.application.dto.record.UpdateColumn1Input;
import jp.spring_boot_template.application.dto.record.UpdateColumn1Output;
import jp.spring_boot_template.application.dto.record.UpdateInput;
import jp.spring_boot_template.application.dto.record.UpdateOutput;
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
  public AddOutput addRecord(final AddInput addInput) {
    recordsRepositoryImpl.addRecord(addInput.column1(), addInput.column2());

    return AddOutput.builder().success(true).build();
  }

  // レコード取得
  public FetchOutput fetchRecord() {
    final Records records = recordsRepositoryImpl.fetchRecord(1);

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
  public UpdateOutput updateRecord(final UpdateInput updateInput) {
    final long recordId = updateInput.recordId();

    // レコードが存在しない場合
    if (Objects.isNull(recordsRepositoryImpl.fetchRecord(recordId))) {
      return UpdateOutput.builder().success(false).build();
    }

    recordsRepositoryImpl.updateRecord(recordId, updateInput.column1(), updateInput.column2());

    return UpdateOutput.builder().success(true).build();
  }

  // レコードカラム1更新
  public UpdateColumn1Output updateRecordColumn1(final UpdateColumn1Input updateColumn1Input) {
    final long recordId = updateColumn1Input.recordId();

    // レコードが存在しない場合
    if (Objects.isNull(recordsRepositoryImpl.fetchRecord(recordId))) {
      return UpdateColumn1Output.builder().success(false).build();
    }

    recordsRepositoryImpl.updateRecordColumn1(recordId, updateColumn1Input.column1());

    return UpdateColumn1Output.builder().success(true).build();
  }

  // レコード削除
  public DeleteOutput deleteRecord(final DeleteInput deleteInput) {
    final long recordId = deleteInput.recordId();

    // レコードが存在しない場合
    if (Objects.isNull(recordsRepositoryImpl.fetchRecord(recordId))) {
      return DeleteOutput.builder().success(false).build();
    }

    recordsRepositoryImpl.deleteRecord(recordId);

    return DeleteOutput.builder().success(true).build();
  }
}
