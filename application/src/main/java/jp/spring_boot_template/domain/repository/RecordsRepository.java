package jp.spring_boot_template.domain.repository;

import jp.spring_boot_template.domain.entity.Records;

public interface RecordsRepository {
    // レコード追加
    void add(final short record1, final String record2);

    // レコード取得
    Records fetch(final int recordId);

    // レコード更新
    void update(final int recordId, final short record1, final String record2);

    // レコード1更新
    void updateRecord1(final int recordId, final short record1);

    // レコード削除
    void delete(final int recordId);
}

