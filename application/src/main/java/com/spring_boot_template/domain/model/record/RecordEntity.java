package com.spring_boot_template.domain.model.record;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RecordEntity {
    private RecordIdValue recordIdValue;
    private Column1Value column1Value;
    private Column2Value column2Value;
}
