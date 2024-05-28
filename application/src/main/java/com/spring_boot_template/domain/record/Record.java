package com.spring_boot_template.domain.record;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RecordEntity {
    private final RecordIdValue recordId;
    private final Column1Value column1;
    private final Column2Value column2;

    public RecordEntity(final RecordIdValue recordIdValue) {
        this(recordIdValue, null, null);
    }

    public RecordEntity(final Column1Value column1Value, final Column2Value column2Value) {
        this(null, column1Value, column2Value);
    }
}
