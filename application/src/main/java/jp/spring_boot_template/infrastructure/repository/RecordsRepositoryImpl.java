package jp.spring_boot_template.infrastructure.repository;

import jp.spring_boot_template.domain.model.entity.Records;
import jp.spring_boot_template.domain.model.repository.RecordsRepository;
import jp.spring_boot_template.infrastructure.query.RecordsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RecordsRepositoryImpl implements RecordsRepository {
  private final RecordsMapper recordsMapper;

  // レコード追加
  public void add(final byte column1, final String column2) {
    recordsMapper.insert(column1, column2);
  }

  // レコード取得
  public Records fetch(final long recordId) {
    return recordsMapper.select(recordId);
  }

  // レコード更新
  public void update(final long recordId, final byte column1, final String column2) {
    recordsMapper.update(recordId, column1, column2);
  }

  // レコードカラム1更新
  public void updateColumn1(final long recordId, final byte column1) {
    recordsMapper.updateColumn1(recordId, column1);
  }

  // レコード削除
  public void delete(final long recordId) {
    recordsMapper.delete(recordId);
  }
}
