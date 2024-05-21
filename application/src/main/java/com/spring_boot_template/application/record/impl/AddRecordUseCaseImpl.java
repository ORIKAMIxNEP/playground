package com.spring_boot_template.application.record.impl;

import com.spring_boot_template.application.record.AddRecordUseCase;
import com.spring_boot_template.infrastructure.record.RecordRdbRepository;
import com.spring_boot_template.presentation.record.request.AddRecordRequest;
import com.spring_boot_template.presentation.record.response.AddRecordResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AddRecordUseCaseImpl implements AddRecordUseCase {
  private final RecordRdbRepository recordRdbRepository;

  // レコード追加
  public AddRecordResponse addRecord(final AddRecordRequest addRecordRequest) {
    recordRdbRepository.addRecord(addRecordRequest.column1(), addRecordRequest.column2());

    return AddRecordResponse.builder().success(true).build();
  }
}
