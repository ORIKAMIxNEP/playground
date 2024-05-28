package com.spring_boot_template.application.usecase.record.impl;

import com.spring_boot_template.application.usecase.record.UpdateRecordUseCase;
import com.spring_boot_template.domain.model.record.Column1Value;
import com.spring_boot_template.domain.model.record.Column2Value;
import com.spring_boot_template.domain.model.record.RecordEntity;
import com.spring_boot_template.domain.model.record.RecordIdValue;
import com.spring_boot_template.infrastructure.record.RecordRdbRepository;
import com.spring_boot_template.presentation.controller.record.request.UpdateRecordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateRecordUseCaseImpl implements UpdateRecordUseCase {
    private final ExistsRecordUseCaseImpl existsRecordUseCase;
    private final RecordRdbRepository recordRdbRepository;

    @Transactional
    public void execute(final UpdateRecordRequest updateRecordRequest) {
        final long recordId = updateRecordRequest.recordId();
        final RecordIdValue recordIdValue = new RecordIdValue(recordId);

        existsRecordUseCase.execute(recordIdValue);

        final Byte column1 = updateRecordRequest.column1();
        final Column1Value column1Value = new Column1Value(column1);
        final String column2 = updateRecordRequest.column2();
        final Column2Value column2Value = new Column2Value(column2);
        final RecordEntity recordEntity =
                new RecordEntity(recordIdValue, column1Value, column2Value);

        recordRdbRepository.updateRecord(recordEntity);
    }
}
