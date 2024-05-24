package com.spring_boot_template.application.record.impl;

import com.spring_boot_template.application.record.AddRecordUseCase;
import com.spring_boot_template.domain.record.Record;
import com.spring_boot_template.infrastructure.record.RecordRdbRepository;
import com.spring_boot_template.presentation.record.request.AddRecordRequest;
import com.spring_boot_template.presentation.record.response.AddRecordResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AddRecordUseCaseImpl implements AddRecordUseCase {
  private final RecordRdbRepository recordRdbRepository;

  @Transactional
  public AddRecordResponse execute(final AddRecordRequest addRecordRequest) {
    final byte column1 = addRecordRequest.column1();
    final String column2 = addRecordRequest.column2();
    final Record record = Record.builder().column1(column1).column2(column2).build();

    recordRdbRepository.addRecord(record);

    return AddRecordResponse.builder().isSuccessful(true).build();
  }
}
