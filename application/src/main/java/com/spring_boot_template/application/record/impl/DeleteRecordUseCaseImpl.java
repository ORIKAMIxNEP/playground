package com.spring_boot_template.application.record.impl;

import com.spring_boot_template.application.record.DeleteRecordUseCase;
import com.spring_boot_template.domain.record.Record;
import com.spring_boot_template.infrastructure.record.RecordRdbRepository;
import com.spring_boot_template.presentation.record.request.DeleteRecordRequest;
import com.spring_boot_template.presentation.record.response.DeleteRecordResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteRecordUseCaseImpl implements DeleteRecordUseCase {
  private final ExistsRecordUseCaseImpl existsRecordUseCase;
  private final RecordRdbRepository recordRdbRepository;

  @Transactional
  public DeleteRecordResponse execute(final DeleteRecordRequest deleteRecordRequest) {
    final long recordId = deleteRecordRequest.recordId();

    // レコードが存在しない場合
    if (!existsRecordUseCase.execute(recordId)) {
      return DeleteRecordResponse.builder().isSuccessful(false).build();
    }

    final Record record = Record.builder().recordId(recordId).build();

    recordRdbRepository.deleteRecord(record);

    return DeleteRecordResponse.builder().isSuccessful(true).build();
  }
}
