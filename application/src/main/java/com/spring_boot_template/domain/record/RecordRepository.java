package com.spring_boot_template.domain.record;

public interface RecordRepository {
  void addRecord(final Record record);

  Record fetchRecord(final Record record);

  void updateRecord(final Record record);

  void updateRecordColumn1(final Record record);

  void deleteRecord(final Record record);
}
