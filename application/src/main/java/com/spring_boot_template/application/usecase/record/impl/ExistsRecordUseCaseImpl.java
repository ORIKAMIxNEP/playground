package com.spring_boot_template.application.usecase.record.impl;

import com.spring_boot_template.application.usecase.record.ExistsRecordUseCase;
import com.spring_boot_template.domain.exception.ValidationException;
import com.spring_boot_template.domain.model.record.RecordIdValue;
import com.spring_boot_template.infrastructure.record.RecordRdbRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class ExistsRecordUseCaseImpl implements ExistsRecordUseCase {
    private final RecordRdbRepository recordRdbRepository;

    @Override
    @Transactional
    public void execute(final RecordIdValue recordIdValue) {
        Optional.ofNullable(recordRdbRepository.fetchRecord(recordIdValue))
                .orElseThrow(() -> new ValidationException("Record Not Found"));
    }
}
