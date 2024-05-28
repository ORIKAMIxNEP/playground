package com.spring_boot_template.application.usecase.record.impl;

import com.spring_boot_template.application.usecase.record.ExistsRecordUseCase;
import com.spring_boot_template.domain.exception.ValidationException;
import com.spring_boot_template.domain.model.record.RecordEntity;
import com.spring_boot_template.domain.model.record.RecordIdValue;
import com.spring_boot_template.infrastructure.record.RecordRdbRepository;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class ExistsRecordUseCaseImpl implements ExistsRecordUseCase {
    private final RecordRdbRepository recordRdbRepository;

    @Transactional
    public void execute(final RecordIdValue recordIdValue) {
        final RecordEntity recordEntity = new RecordEntity(recordIdValue);

        // レコードが存在しない場合
        if (Objects.isNull(recordRdbRepository.fetchRecord(recordEntity))) {
            throw new ValidationException("Record Not Found");
        }
    }
}
