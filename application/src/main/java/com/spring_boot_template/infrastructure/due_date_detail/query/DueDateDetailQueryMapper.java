package com.spring_boot_template.infrastructure.due_date_detail.query;

import com.spring_boot_template.application.due_date_detail.query.DueDateDetailQueryDto;
import com.spring_boot_template.domain.model.task.value.TaskId;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
interface DueDateDetailQueryMapper {
    DueDateDetailQueryDto selectDueDateDetailByTaskId(@Param("taskId") final TaskId taskId);
}
