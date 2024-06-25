package com.spring_boot_template.infrastructure.project.task.due_date_detail.query;

import com.spring_boot_template.application.project.task.due_date_detail.query.dto.DueDateDetailQueryDto;
import com.spring_boot_template.domain.model.project.task.value.TaskId;
import org.apache.ibatis.annotations.Mapper;

@Mapper
interface DueDateDetailQueryMapper {
    DueDateDetailQueryDto selectDueDateDetailByTaskId(final TaskId taskId);
}
