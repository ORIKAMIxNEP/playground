package com.spring_boot_template.infrastructure.record;

import com.spring_boot_template.domain.model.record.RecordEntity;
import com.spring_boot_template.domain.model.record.RecordIdValue;
import org.apache.ibatis.annotations.Mapper;

@Mapper
interface RecordMapper {
    void addRecord(final RecordEntity recordEntity);

    RecordEntity fetchRecord(final RecordIdValue recordIdValue);

    void updateRecord(final RecordEntity recordEntity);

    void deleteRecord(final RecordIdValue recordIdValue);
}
