package com.spring_boot_template.infrastructure.task.query;

import com.spring_boot_template.application.task.query.TaskQueryDto;
import com.spring_boot_template.domain.model.project.value.ProjectId;
import com.spring_boot_template.domain.model.task.value.TaskId;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
interface TaskQueryMapper {
    TaskQueryDto selectTaskByProjectIdAndTaskId(
            @Param("projectId") final ProjectId projectId, @Param("taskId") final TaskId taskId);
}
