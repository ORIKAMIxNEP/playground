package com.spring_boot_template.infrastructure.project.task.due_date_detail;

import com.spring_boot_template.domain.model.project.task.due_date_detail.DueDateDetail;
import com.spring_boot_template.domain.model.project.task.value.TaskId;
import com.spring_boot_template.domain.model.project.value.ProjectId;
import com.spring_boot_template.infrastructure.project.task.due_date_detail.dto.DueDateDetailDto;
import java.util.ArrayList;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DueDateDetailMapper {
    void insertDueDateDetail(final TaskId taskId, final DueDateDetail dueDateDetail);

    ArrayList<DueDateDetailDto> selectDueDateDetailsByProjectId(final ProjectId projectId);
}
