package com.spring_boot_template.application.record.impl;

import com.spring_boot_template.application.record.FetchRecordUseCase;
import com.spring_boot_template.domain.record.Record;
import com.spring_boot_template.infrastructure.record.RecordRdbRepository;
import com.spring_boot_template.presentation.record.response.FetchRecordResponse;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class FetchRecordUseCaseImpl implements FetchRecordUseCase {
  private final RecordRdbRepository recordRdbRepository;

  // レコード取得
  public FetchRecordResponse fetchRecord() {
    final Record record = recordRdbRepository.fetchRecord(1);

    // レコードが存在しない場合
    if (Objects.isNull(record)) {
      return FetchRecordResponse.builder().success(false).column1(null).column2(null).build();
    }

    return FetchRecordResponse.builder()
        .success(true)
        .column1(record.getColumn1())
        .column2(record.getColumn2())
        .build();
  }
}
