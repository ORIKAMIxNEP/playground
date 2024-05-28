package com.spring_boot_template.application.usecase.record;

import com.spring_boot_template.domain.model.record.RecordIdValue;

public interface ExistsRecordUseCase {
    void execute(final RecordIdValue recordIdValue);
}
