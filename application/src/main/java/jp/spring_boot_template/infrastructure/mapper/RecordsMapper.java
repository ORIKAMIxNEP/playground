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
  // レコード追加
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
  void add(final String record1, final String record2);

  // レコード取得
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
  Records fetch(final int recordId);

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
  void update(final int recordId, final String record1, final String record2);

  // レコード1更新
  @Update(
      """
      UPDATE
        records
      SET
        record1 = #{record1}
      WHERE
        record_id = #{recordId}
      """)
  void updateRecord1(final int recordId, final String record1);

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
