package com.spring_boot_template.infrastructure.record;

import com.spring_boot_template.domain.record.Record;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface RecordMapper {
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
  void addRecord(final Record record);

  @Select(
      """
      SELECT
        record_id, column1, column2
      FROM
        records
      WHERE
        record_id = #{recordId}
      FOR
        UPDATE
      """)
  @Results(
      id = "Record",
      value = {
        @Result(column = "record_id", property = "recordId"),
        @Result(column = "column1", property = "column1"),
        @Result(column = "column2", property = "column2")
      })
  Record fetchRecord(final Record record);

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
  void updateRecord(final Record record);

  @Delete(
      """
      DELETE FROM
        records
      WHERE
        record_id = #{recordId}
      """)
  void deleteRecord(final Record record);
}
