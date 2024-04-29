package jp.spring_boot_template.infrastructure.repository;

import jp.spring_boot_template.domain.entity.Records;
import jp.spring_boot_template.infrastructure.mapper.RecordsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RecordsRepository {
  private final RecordsMapper recordsMapper;

  // レコード追加
  public void add(final String record1, final String record2) {
    recordsMapper.add(record1, record2);
  }

  // レコード取得
  public Records fetch(final int recordId) {
    return recordsMapper.fetch(recordId);
  }

  // レコード更新
  public void update(final int recordId, final String record1, final String record2) {
    recordsMapper.update(recordId, record1, record2);
  }

  // レコード1更新
  public void updateRecord1(final int recordId, final String record1) {
    recordsMapper.updateRecord1(recordId, record1);
  }

  // レコード削除
  public void delete(final int recordId) {
    recordsMapper.delete(recordId);
  }
}
