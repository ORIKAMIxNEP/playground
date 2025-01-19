package com.spring_boot_template.infrastructure.task;

import static com.spring_boot_template.jooq.Tables.DUE_DATE_DETAILS;
import static com.spring_boot_template.jooq.Tables.TASKS;
import static com.spring_boot_template.jooq.Tables.TASK_ACCOUNT_ASSIGNMENTS;

import com.spring_boot_template.application.due_date_detail.query.DueDateDetailQueryDto;
import com.spring_boot_template.application.task.query.FetchTaskQueryDto;
import com.spring_boot_template.application.task.query.TaskQueryService;
import com.spring_boot_template.domain.exception.ResourceNotFoundException;
import com.spring_boot_template.domain.model.project.value.ProjectId;
import com.spring_boot_template.domain.model.task.value.TaskId;
import com.spring_boot_template.presentation.controller.due_date_detail.response.DueDateDetailResponse;
import com.spring_boot_template.presentation.controller.task.response.FetchTaskResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
@RequiredArgsConstructor
final class TaskQueryServiceImpl implements TaskQueryService {
    private final DSLContext dslContext;

    @Override
    public FetchTaskResponse findTaskByProjectIdAndTaskId(
            final ProjectId projectId, final TaskId taskId) {
        final List<FetchTaskQueryDto> fetchTaskQueryDtos =
                selectTaskByProjectIdAndTaskId(projectId.value(), taskId.value());
        if (CollectionUtils.isEmpty(fetchTaskQueryDtos)) {
            throw new ResourceNotFoundException("Task is not found");
        }
        final FetchTaskQueryDto fetchTaskQueryDto = fetchTaskQueryDtos.get(0);
        final String taskName = fetchTaskQueryDto.taskName();
        final String status = fetchTaskQueryDto.status();
        final String[] accountIds =
                fetchTaskQueryDtos.stream()
                        .map(FetchTaskQueryDto::accountId)
                        .toArray(String[]::new);

        final DueDateDetailQueryDto dueDateDetailQueryDto =
                selectDueDateDetailByTaskId(taskId.value());
        final String dueDate = dueDateDetailQueryDto.dueDate();
        final int postponeCount = dueDateDetailQueryDto.postponeCount();
        final int maxPostponeCount = dueDateDetailQueryDto.maxPostponeCount();
        final DueDateDetailResponse dueDateDetailResponse =
                new DueDateDetailResponse(dueDate, postponeCount, maxPostponeCount);

        return new FetchTaskResponse(taskName, status, accountIds, dueDateDetailResponse);
    }

    private List<FetchTaskQueryDto> selectTaskByProjectIdAndTaskId(
            final String projectId, final String taskId) {
        return dslContext
                .select(TASKS.TASK_NAME, TASKS.STATUS, TASK_ACCOUNT_ASSIGNMENTS.ACCOUNT_ID)
                .from(TASKS)
                .leftJoin(TASK_ACCOUNT_ASSIGNMENTS)
                .on(TASKS.TASK_ID.eq(TASK_ACCOUNT_ASSIGNMENTS.TASK_ID))
                .where(TASKS.PROJECT_ID.eq(projectId))
                .and(TASKS.TASK_ID.eq(taskId))
                .fetchInto(FetchTaskQueryDto.class);
    }

    private DueDateDetailQueryDto selectDueDateDetailByTaskId(final String taskId) {
        return dslContext
                .select(
                        DUE_DATE_DETAILS.DUE_DATE,
                        DUE_DATE_DETAILS.POSTPONE_COUNT,
                        DUE_DATE_DETAILS.MAX_POSTPONE_COUNT)
                .from(DUE_DATE_DETAILS)
                .where(DUE_DATE_DETAILS.TASK_ID.eq(taskId))
                .fetchOneInto(DueDateDetailQueryDto.class);
    }
}
