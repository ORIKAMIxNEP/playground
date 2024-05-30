package com.spring_boot_template.domain.model.record;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class RecordEntity {
    RecordIdValue recordIdValue;
    Column1Value column1Value;
    Column2Value column2Value;

    public RecordEntity(final RecordIdValue recordIdValue) {
        this(recordIdValue, null, null);
    }

    public RecordEntity(final Column1Value column1Value, final Column2Value column2Value) {
        this(null, column1Value, column2Value);
    }
}
