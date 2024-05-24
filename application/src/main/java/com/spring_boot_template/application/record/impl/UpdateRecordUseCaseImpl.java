package com.spring_boot_template.application.record.impl;

import com.spring_boot_template.application.record.UpdateRecordUseCase;
import com.spring_boot_template.domain.record.Record;
import com.spring_boot_template.infrastructure.record.RecordRdbRepository;
import com.spring_boot_template.presentation.record.request.UpdateRecordRequest;
import com.spring_boot_template.presentation.record.response.UpdateRecordResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateRecordUseCaseImpl implements UpdateRecordUseCase {
  private final ExistsRecordUseCaseImpl existsRecordUseCase;
  private final RecordRdbRepository recordRdbRepository;

  @Transactional
  public UpdateRecordResponse execute(final UpdateRecordRequest updateRecordRequest) {
    final long recordId = updateRecordRequest.recordId();

    // レコードが存在しない場合
    if (!existsRecordUseCase.execute(recordId)) {
      return UpdateRecordResponse.builder().isSuccessful(false).build();
    }

    final Byte column1 = updateRecordRequest.column1();
    final String column2 = updateRecordRequest.column2();
    final Record record =
        Record.builder().recordId(recordId).column1(column1).column2(column2).build();

    recordRdbRepository.updateRecord(record);

    return UpdateRecordResponse.builder().isSuccessful(true).build();
  }
}
