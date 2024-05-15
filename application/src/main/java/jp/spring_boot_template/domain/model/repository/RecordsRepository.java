package jp.spring_boot_template.domain.model.repository;

import jp.spring_boot_template.domain.model.entity.Records;

public interface RecordsRepository {
  // レコード追加
  void add(final byte column1, final String column2);

  // レコード取得
  Records fetch(final long recordId);

  // レコード更新
  void update(final long recordId, final byte column1, final String column2);

  // レコードカラム1更新
  void updateColumn1(final long recordId, final byte column1);

  // レコード削除
  void delete(final long recordId);
}
