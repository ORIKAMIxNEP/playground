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
  public void addRecord(final byte column1, final String column2) {
    recordsMapper.addRecord(column1, column2);
  }

  // レコード取得
  public Records fetchRecord(final long recordId) {
    return recordsMapper.fetchRecord(recordId);
  }

  // レコード更新
  public void updateRecord(final long recordId, final byte column1, final String column2) {
    recordsMapper.updateRecord(recordId, column1, column2);
  }

  // レコードカラム1更新
  public void updateRecordColumn1(final long recordId, final byte column1) {
    recordsMapper.updateRecordColumn1(recordId, column1);
  }

  // レコード削除
  public void deleteRecord(final long recordId) {
    recordsMapper.deleteRecord(recordId);
  }
}
