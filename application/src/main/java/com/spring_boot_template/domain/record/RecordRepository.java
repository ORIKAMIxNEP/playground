package com.spring_boot_template.domain.record;

public interface RecordRepository {
    void addRecord(final RecordEntity recordEntity);

    RecordEntity fetchRecord(final RecordEntity recordEntity);

    void updateRecord(final RecordEntity recordEntity);

    void deleteRecord(final RecordEntity recordEntity);
}
