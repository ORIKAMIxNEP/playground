package com.spring_boot_template.domain.model.record;

public interface RecordRepository {
    void addRecord(final RecordEntity recordEntity);

    RecordEntity fetchRecord(final RecordIdValue recordIdValue);

    void updateRecord(final RecordEntity recordEntity);

    void deleteRecord(final RecordIdValue recordIdValue);
}
