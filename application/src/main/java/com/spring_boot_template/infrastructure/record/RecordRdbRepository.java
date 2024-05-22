package com.spring_boot_template.infrastructure.record;

import com.spring_boot_template.domain.record.Record;
import com.spring_boot_template.domain.record.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RecordRdbRepository implements RecordRepository {
  private final RecordMapper recordMapper;

  public void addRecord(final Record record) {
    recordMapper.addRecord(record);
  }

  public Record fetchRecord(final Record record) {
    return recordMapper.fetchRecord(record);
  }

  public void updateRecord(final Record record) {
    recordMapper.updateRecord(record);
  }

  public void deleteRecord(final Record record) {
    recordMapper.deleteRecord(record);
  }
}
