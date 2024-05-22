package com.spring_boot_template.application.record.impl;

import com.spring_boot_template.application.record.ExistsRecordUseCase;
import com.spring_boot_template.domain.record.Record;
import com.spring_boot_template.infrastructure.record.RecordRdbRepository;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ExistsRecordUseCaseImpl implements ExistsRecordUseCase {
  private final RecordRdbRepository recordRdbRepository;

  // レコード存在確認
  @Transactional
  public boolean execute(final long recordId) {
    final Record record = Record.builder().recordId(recordId).build();

    return Objects.nonNull(recordRdbRepository.fetchRecord(record));
  }
}
