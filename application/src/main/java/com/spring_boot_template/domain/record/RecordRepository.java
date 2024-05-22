package com.spring_boot_template.domain.record;

public interface RecordRepository {
  // レコード追加
  void addRecord(final Record record);

  // レコード取得
  Record fetchRecord(final Record record);

  // レコード更新
  void updateRecord(final Record record);

  // レコードカラム1更新
  void updateRecordColumn1(final Record record);

  // レコード削除
  void deleteRecord(final Record record);
}
