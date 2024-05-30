package com.spring_boot_template.application.usecase.record.impl;

import com.spring_boot_template.application.usecase.record.FetchRecordUseCase;
import com.spring_boot_template.domain.exception.ValidationException;
import com.spring_boot_template.domain.model.record.Column1Value;
import com.spring_boot_template.domain.model.record.Column2Value;
import com.spring_boot_template.domain.model.record.RecordEntity;
import com.spring_boot_template.domain.model.record.RecordIdValue;
import com.spring_boot_template.infrastructure.record.RecordRdbRepository;
import com.spring_boot_template.presentation.controller.record.response.FetchRecordResponse;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FetchRecordUseCaseImpl implements FetchRecordUseCase {
    private final RecordRdbRepository recordRdbRepository;

    @Transactional
    public FetchRecordResponse execute(final String recordId) {
        final RecordIdValue recordIdValue = new RecordIdValue(recordId);
        final RecordEntity recordEntity = recordRdbRepository.fetchRecord(recordIdValue);

        // レコードが存在しない場合
        if (Objects.isNull(recordEntity)) {
            throw new ValidationException("Record Not Found");
        }

        final Column1Value column1Value = recordEntity.getColumn1Value();
        final byte column1 = column1Value.getValue();
        final Column2Value column2Value = recordEntity.getColumn2Value();
        final String column2 = column2Value.getValue();

        return new FetchRecordResponse(column1, column2);
    }
}
