package com.spring_boot_template.domain.record;

public interface RecordRepository {
  // レコード追加
  void addRecord(final byte column1, final String column2);

  // レコード取得
  Record fetchRecord(final long recordId);

  // レコード更新
  void updateRecord(final long recordId, final byte column1, final String column2);

  // レコードカラム1更新
  void updateRecordColumn1(final long recordId, final byte column1);

  // レコード削除
  void deleteRecord(final long recordId);
}
