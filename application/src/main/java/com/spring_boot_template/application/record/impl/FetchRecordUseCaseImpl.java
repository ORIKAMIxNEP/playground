package com.spring_boot_template.application.record.impl;

import com.spring_boot_template.application.record.FetchRecordUseCase;
import com.spring_boot_template.domain.record.Record;
import com.spring_boot_template.infrastructure.record.RecordRdbRepository;
import com.spring_boot_template.presentation.record.request.FetchRecordRequest;
import com.spring_boot_template.presentation.record.response.FetchRecordResponse;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FetchRecordUseCaseImpl implements FetchRecordUseCase {
  private final RecordRdbRepository recordRdbRepository;

  @Transactional
  public FetchRecordResponse execute(final FetchRecordRequest fetchRecordRequest) {
    final long recordId = fetchRecordRequest.recordId();
    final Record recordInput = Record.builder().recordId(recordId).build();
    final Record recordOutput = recordRdbRepository.fetchRecord(recordInput);

    // レコードが存在しない場合
    if (Objects.isNull(recordOutput)) {
      return FetchRecordResponse.builder().successful(false).column1(null).column2(null).build();
    }

    final byte column1 = recordOutput.column1();
    final String column2 = recordOutput.column2();

    return FetchRecordResponse.builder().successful(true).column1(column1).column2(column2).build();
  }
}
