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
  public void addRecord(final byte column1, final String column2) {
    recordMapper.addRecord(column1, column2);
  }

  // レコード取得
  public Record fetchRecord(final long recordId) {
    return recordMapper.fetchRecord(recordId);
  }

  // レコード更新
  public void updateRecord(final long recordId, final byte column1, final String column2) {
    recordMapper.updateRecord(recordId, column1, column2);
  }

  // レコードカラム1更新
  public void updateRecordColumn1(final long recordId, final byte column1) {
    recordMapper.updateRecordColumn1(recordId, column1);
  }

  // レコード削除
  public void deleteRecord(final long recordId) {
    recordMapper.deleteRecord(recordId);
  }
}
