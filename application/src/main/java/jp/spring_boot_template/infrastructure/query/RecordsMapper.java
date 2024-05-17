package jp.spring_boot_template.infrastructure.query;

import jp.spring_boot_template.domain.model.entity.Records;
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
          column1, column2
        )
      VALUES(
        #{column1}, #{column2}
      )
      """)
  void addRecord(final byte column1, final String column2);

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
        @Result(column = "column1", property = "column1"),
        @Result(column = "column2", property = "column2")
      })
  Records fetchRecord(final long recordId);

  // レコード更新
  @Update(
      """
      UPDATE
        records
      SET
        column1 = #{column1},
        column2 = #{column2}
      WHERE
        record_id = #{recordId}
      """)
  void updateRecord(final long recordId, final byte column1, final String column2);

  // レコードカラム1更新
  @Update(
      """
      UPDATE
        records
      SET
        column1 = #{column1}
      WHERE
        record_id = #{recordId}
      """)
  void updateRecordColumn1(final long recordId, final byte column1);

  // レコード削除
  @Delete(
      """
      DELETE FROM
        records
      WHERE
        record_id = #{recordId}
      """)
  void deleteRecord(final long recordId);
}
