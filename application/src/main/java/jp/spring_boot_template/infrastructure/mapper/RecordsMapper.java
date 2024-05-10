package jp.spring_boot_template.infrastructure.mapper;

import jp.spring_boot_template.domain.entity.Records;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface RecordsMapper {
  // レコード挿入
  @Insert(
      """
      INSERT INTO
        records(
          record1, record2
        )
      VALUES(
        #{record1}, #{record2}
      )
      """)
  void insert(final short record1, final String record2);

  // レコード選択
  @Select(
      """
      SELECT
        *
      FROM
        records
      WHERE
        record_id = #{recordId}
      """)
  @Results(
      id = "Records",
      value = {
        @Result(column = "record_id", property = "recordId"),
        @Result(column = "record1", property = "record1"),
        @Result(column = "record2", property = "record2")
      })
  Records select(final int recordId);

  // レコード更新
  @Update(
      """
      UPDATE
        records
      SET
        record1 = #{record1},
        record2 = #{record2}
      WHERE
        record_id = #{recordId}
      """)
  void update(final int recordId, final short record1, final String record2);

  // レコードレコード1更新
  @Update(
      """
      UPDATE
        records
      SET
        record1 = #{record1}
      WHERE
        record_id = #{recordId}
      """)
  void updateRecord1(final int recordId, final short record1);

  // レコード削除
  @Delete(
      """
      DELETE FROM
        records
      WHERE
        record_id = #{recordId}
      """)
  void delete(final int recordId);
}
