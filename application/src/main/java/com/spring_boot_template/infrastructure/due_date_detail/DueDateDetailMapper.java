package com.spring_boot_template.infrastructure.due_date_detail;

import com.spring_boot_template.domain.model.due_date_detail.DueDateDetail;
import com.spring_boot_template.domain.model.project.value.ProjectId;
import com.spring_boot_template.domain.model.task.value.TaskId;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DueDateDetailMapper {
    void insertDueDateDetail(
            @Param("taskId") final TaskId taskId, final DueDateDetail dueDateDetail);

    List<DueDateDetailDto> selectDueDateDetailsByProjectId(
            @Param("projectId") final ProjectId projectId);
}
