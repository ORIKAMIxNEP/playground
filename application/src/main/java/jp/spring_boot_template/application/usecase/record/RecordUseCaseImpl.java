package jp.spring_boot_template.application.usecase.record;

import java.util.Objects;
import jp.spring_boot_template.application.dto.record.AddRecordInput;
import jp.spring_boot_template.application.dto.record.AddRecordOutput;
import jp.spring_boot_template.application.dto.record.DeleteRecordInput;
import jp.spring_boot_template.application.dto.record.DeleteRecordOutput;
import jp.spring_boot_template.application.dto.record.FetchRecordOutput;
import jp.spring_boot_template.application.dto.record.UpdateRecordColumn1Input;
import jp.spring_boot_template.application.dto.record.UpdateRecordColumn1Output;
import jp.spring_boot_template.application.dto.record.UpdateRecordInput;
import jp.spring_boot_template.application.dto.record.UpdateRecordOutput;
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
  public AddRecordOutput addRecord(final AddRecordInput addRecordInput) {
    recordsRepositoryImpl.addRecord(addRecordInput.column1(), addRecordInput.column2());

    return AddRecordOutput.builder().success(true).build();
  }

  // レコード取得
  public FetchRecordOutput fetchRecord() {
    final Records records = recordsRepositoryImpl.fetch(1);

    // レコードが存在しない場合
    if (Objects.isNull(records)) {
      return FetchRecordOutput.builder().success(false).column1(null).column2(null).build();
    }

    return FetchRecordOutput.builder()
        .success(true)
        .column1(records.getColumn1())
        .column2(records.getColumn2())
        .build();
  }

  // レコード更新
  public UpdateRecordOutput updateRecord(final UpdateRecordInput updateRecordInput) {
    final long recordId = updateRecordInput.recordId();

    // レコードが存在しない場合
    if (Objects.isNull(recordsRepositoryImpl.fetchRecord(recordId))) {
      return UpdateRecordOutput.builder().success(false).build();
    }

    recordsRepositoryImpl.updateRecord(
        recordId, updateRecordInput.column1(), updateRecordInput.column2());

    return UpdateRecordOutput.builder().success(true).build();
  }

  // レコードカラム1更新
  public UpdateRecordColumn1Output updateColumn1Record(
      final UpdateRecordColumn1Input updateRecordColumn1Input) {
    final long recordId = updateRecordColumn1Input.recordId();

    // レコードが存在しない場合
    if (Objects.isNull(recordsRepositoryImpl.fetchRecord(recordId))) {
      return UpdateRecordColumn1Output.builder().success(false).build();
    }

    recordsRepositoryImpl.updateRecordColumn1(recordId, updateRecordColumn1Input.column1());

    return UpdateRecordColumn1Output.builder().success(true).build();
  }

  // レコード削除
  public DeleteRecordOutput deleteRecord(final DeleteRecordInput deleteRecordInput) {
    final long recordId = deleteRecordInput.recordId();

    // レコードが存在しない場合
    if (Objects.isNull(recordsRepositoryImpl.fetchRecord(recordId))) {
      return DeleteRecordOutput.builder().success(false).build();
    }

    recordsRepositoryImpl.deleteRecord(recordId);

    return DeleteRecordOutput.builder().success(true).build();
  }
}
