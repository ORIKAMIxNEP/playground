package com.spring_boot_template.application.usecase.record.impl;

import com.spring_boot_template.application.usecase.record.DeleteRecordUseCase;
import com.spring_boot_template.domain.model.record.RecordEntity;
import com.spring_boot_template.domain.model.record.RecordIdValue;
import com.spring_boot_template.infrastructure.record.RecordRdbRepository;
import com.spring_boot_template.presentation.controller.record.request.DeleteRecordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteRecordUseCaseImpl implements DeleteRecordUseCase {
    private final ExistsRecordUseCaseImpl existsRecordUseCase;
    private final RecordRdbRepository recordRdbRepository;

    @Transactional
    public void execute(final DeleteRecordRequest deleteRecordRequest) {
        final long recordId = deleteRecordRequest.recordId();
        final RecordIdValue recordIdValue = new RecordIdValue(recordId);

        existsRecordUseCase.execute(recordIdValue);

        final RecordEntity recordEntity = new RecordEntity(recordIdValue);

        recordRdbRepository.deleteRecord(recordEntity);
    }
}
