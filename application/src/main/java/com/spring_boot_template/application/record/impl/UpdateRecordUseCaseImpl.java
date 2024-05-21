package com.spring_boot_template.application.record.impl;

import com.spring_boot_template.application.record.UpdateRecordUseCase;
import com.spring_boot_template.infrastructure.record.RecordRdbRepository;
import com.spring_boot_template.presentation.record.request.UpdateRecordRequest;
import com.spring_boot_template.presentation.record.response.UpdateRecordResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateRecordUseCaseImpl implements UpdateRecordUseCase {
  private final RecordRdbRepository recordRdbRepository;

  // レコード更新
  public UpdateRecordResponse updateRecord(final UpdateRecordRequest updateRecordRequest) {
    final long recordId = updateRecordRequest.recordId();

    // レコードが存在しない場合
    if (Objects.isNull(recordRdbRepository.fetchRecord(recordId))) {
      return UpdateRecordResponse.builder().success(false).build();
    }

    recordRdbRepository.updateRecord(
        recordId, updateRecordRequest.column1(), updateRecordRequest.column2());

    return UpdateRecordResponse.builder().success(true).build();
  }
}
