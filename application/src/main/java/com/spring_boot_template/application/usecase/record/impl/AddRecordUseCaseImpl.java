package com.spring_boot_template.application.usecase.record.impl;

import com.spring_boot_template.application.usecase.record.AddRecordUseCase;
import com.spring_boot_template.domain.model.record.Column1Value;
import com.spring_boot_template.domain.model.record.Column2Value;
import com.spring_boot_template.domain.model.record.RecordEntity;
import com.spring_boot_template.domain.model.record.RecordIdValue;
import com.spring_boot_template.domain.shared.IdGenerator;
import com.spring_boot_template.infrastructure.record.RecordRdbRepository;
import com.spring_boot_template.presentation.controller.record.request.AddRecordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AddRecordUseCaseImpl implements AddRecordUseCase {
    private final RecordRdbRepository recordRdbRepository;

    @Override
    @Transactional
    public void execute(final AddRecordRequest addRecordRequest) {
        final String uuid = IdGenerator.generate();
        final RecordIdValue recordIdValue = new RecordIdValue(uuid);
        final byte column1 = addRecordRequest.column1();
        final Column1Value column1Value = new Column1Value(column1);
        final String column2 = addRecordRequest.column2();
        final Column2Value column2Value = new Column2Value(column2);
        final RecordEntity recordEntity =
                new RecordEntity(recordIdValue, column1Value, column2Value);

        recordRdbRepository.addRecord(recordEntity);
    }
}
