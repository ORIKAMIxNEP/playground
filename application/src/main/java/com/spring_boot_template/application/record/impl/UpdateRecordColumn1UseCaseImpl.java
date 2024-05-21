package com.spring_boot_template.application.record.impl;

import com.spring_boot_template.application.dto.record.AddRecordInput;
import com.spring_boot_template.application.dto.record.AddRecordOutput;
import com.spring_boot_template.application.dto.record.DeleteRecordInput;
import com.spring_boot_template.application.dto.record.DeleteRecordOutput;
import com.spring_boot_template.application.dto.record.FetchRecordOutput;
import com.spring_boot_template.application.dto.record.UpdateRecordColumn1Input;
import com.spring_boot_template.application.dto.record.UpdateRecordColumn1Output;
import com.spring_boot_template.application.dto.record.UpdateRecordInput;
import com.spring_boot_template.application.dto.record.UpdateRecordOutput;
import com.spring_boot_template.application.record.RecordUseCase;
import com.spring_boot_template.domain.record.Record;
import com.spring_boot_template.infrastructure.record.RecordRdbRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateRecordColumn1UseCaseImpl implements RecordUseCase {
  private final RecordRdbRepository recordRdbRepository;

  // レコード追加
  public AddRecordOutput addRecord(final AddRecordInput addRecordInput) {
    recordRdbRepository.addRecord(addRecordInput.column1(), addRecordInput.column2());

    return AddRecordOutput.builder().success(true).build();
  }

  // レコード取得
  public FetchRecordOutput fetchRecord() {
    final Record record = recordRdbRepository.fetchRecord(1);

    // レコードが存在しない場合
    if (Objects.isNull(record)) {
      return FetchRecordOutput.builder().success(false).column1(null).column2(null).build();
    }

    return FetchRecordOutput.builder()
        .success(true)
        .column1(record.getColumn1())
        .column2(record.getColumn2())
        .build();
  }

  // レコード更新
  public UpdateRecordOutput updateRecord(final UpdateRecordInput updateRecordInput) {
    final long recordId = updateRecordInput.recordId();

    // レコードが存在しない場合
    if (Objects.isNull(recordRdbRepository.fetchRecord(recordId))) {
      return UpdateRecordOutput.builder().success(false).build();
    }

    recordRdbRepository.updateRecord(
        recordId, updateRecordInput.column1(), updateRecordInput.column2());

    return UpdateRecordOutput.builder().success(true).build();
  }

  // レコードカラム1更新
  public UpdateRecordColumn1Output updateRecordColumn1(
      final UpdateRecordColumn1Input updateRecordColumn1Input) {
    final long recordId = updateRecordColumn1Input.recordId();

    // レコードが存在しない場合
    if (Objects.isNull(recordRdbRepository.fetchRecord(recordId))) {
      return UpdateRecordColumn1Output.builder().success(false).build();
    }

    recordRdbRepository.updateRecordColumn1(recordId, updateRecordColumn1Input.column1());

    return UpdateRecordColumn1Output.builder().success(true).build();
  }

  // レコード削除
  public DeleteRecordOutput deleteRecord(final DeleteRecordInput deleteRecordInput) {
    final long recordId = deleteRecordInput.recordId();

    // レコードが存在しない場合
    if (Objects.isNull(recordRdbRepository.fetchRecord(recordId))) {
      return DeleteRecordOutput.builder().success(false).build();
    }

    recordRdbRepository.deleteRecord(recordId);

    return DeleteRecordOutput.builder().success(true).build();
  }
}
