package com.spring_boot_template.application.record.impl;

import com.spring_boot_template.application.record.UpdateRecordColumn1UseCase;
import com.spring_boot_template.domain.record.Record;
import com.spring_boot_template.infrastructure.record.RecordRdbRepository;
import com.spring_boot_template.presentation.record.request.UpdateRecordColumn1Request;
import com.spring_boot_template.presentation.record.response.UpdateRecordColumn1Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateRecordColumn1UseCaseImpl implements UpdateRecordColumn1UseCase {
  private final RecordRdbRepository recordRdbRepository;
  private final ExistsRecordUseCaseImpl existsRecordUseCase;

  @Transactional
  public UpdateRecordColumn1Response execute(
      final UpdateRecordColumn1Request updateRecordColumn1Request) {
    final long recordId = updateRecordColumn1Request.recordId();

    // レコードが存在しない場合
    if (!existsRecordUseCase.execute(recordId)) {
      return UpdateRecordColumn1Response.builder().successful(false).build();
    }

    final byte column1 = updateRecordColumn1Request.column1();
    final Record record = Record.builder().recordId(recordId).column1(column1).build();

    recordRdbRepository.updateRecordColumn1(record);

    return UpdateRecordColumn1Response.builder().successful(true).build();
  }
}
