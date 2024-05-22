package com.spring_boot_template.infrastructure.record;

import com.spring_boot_template.domain.record.Record;
import com.spring_boot_template.domain.record.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RecordRdbRepository implements RecordRepository {
  private final RecordMapper recordMapper;

  // レコード追加
  public void addRecord(final Record record) {
    recordMapper.addRecord(record);
  }

  // レコード取得
  public Record fetchRecord(final Record record) {
    return recordMapper.fetchRecord(record);
  }

  // レコード更新
  public void updateRecord(final Record record) {
    recordMapper.updateRecord(record);
  }

  // レコードカラム1更新
  public void updateRecordColumn1(final Record record) {
    recordMapper.updateRecordColumn1(record);
  }

  // レコード削除
  public void deleteRecord(final Record record) {
    recordMapper.deleteRecord(record);
  }
}
