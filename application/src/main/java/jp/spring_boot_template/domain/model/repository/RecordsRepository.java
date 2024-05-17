package jp.spring_boot_template.domain.model.repository;

import jp.spring_boot_template.domain.model.entity.Records;

public interface RecordsRepository {
  // レコード追加
  void addRecord(final byte column1, final String column2);

  // レコード取得
  Records fetchRecord(final long recordId);

  // レコード更新
  void updateRecord(final long recordId, final byte column1, final String column2);

  // レコードカラム1更新
  void updateRecordColumn1(final long recordId, final byte column1);

  // レコード削除
  void deleteRecord(final long recordId);
}
