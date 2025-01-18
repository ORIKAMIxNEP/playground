package com.spring_boot_template.infrastructure.task.query;

import com.spring_boot_template.application.task.query.TaskQueryService;
import com.spring_boot_template.domain.exception.ResourceNotFoundException;
import com.spring_boot_template.domain.model.project.value.ProjectId;
import com.spring_boot_template.domain.model.task.value.TaskId;
import com.spring_boot_template.presentation.controller.due_date_detail.response.DueDateDetailResponse;
import com.spring_boot_template.presentation.controller.task.response.FetchTaskResponse;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record3;
import org.jooq.Result;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import static com.spring_boot_template.jooq.Tables.DUE_DATE_DETAILS;
import static com.spring_boot_template.jooq.Tables.TASKS;
import static com.spring_boot_template.jooq.Tables.TASK_ACCOUNT_ASSIGNMENTS;

@Service
@RequiredArgsConstructor
final class TaskQueryServiceImpl implements TaskQueryService {
    private final DSLContext dslContext;

    @Override
    public FetchTaskResponse findTaskByProjectIdAndTaskId(
            final ProjectId projectId, final TaskId taskId) {
        final Result<Record3<String, String, String>> taskRecords =
                selectTaskByProjectIdAndTaskId(projectId.value(), taskId.value());

        if (CollectionUtils.isEmpty(taskRecords)) {
            throw new ResourceNotFoundException("Task is not found");
        }

        final Record taskRecord = taskRecords.get(0);
        final String taskName = taskRecord.get(TASKS.TASK_NAME);
        final String status = taskRecord.get(TASKS.STATUS);
        final String[] accountIds =
                taskRecords.getValues(TASK_ACCOUNT_ASSIGNMENTS.ACCOUNT_ID).toArray(new String[0]);

        final DueDateDetailResponse dueDateDetailResponse =
                selectDueDateDetailByTaskId(taskId.value());

        return new FetchTaskResponse(taskName, status, accountIds, dueDateDetailResponse);
    }

    private Result<Record3<String, String, String>> selectTaskByProjectIdAndTaskId(
            final String projectId, final String taskId) {
        return dslContext
                .select(TASKS.TASK_NAME, TASKS.STATUS, TASK_ACCOUNT_ASSIGNMENTS.ACCOUNT_ID)
                .from(TASKS)
                .leftJoin(TASK_ACCOUNT_ASSIGNMENTS)
                .on(TASKS.TASK_ID.eq(TASK_ACCOUNT_ASSIGNMENTS.TASK_ID))
                .where(TASKS.PROJECT_ID.eq(projectId))
                .and(TASKS.TASK_ID.eq(taskId))
                .fetch();
    }

    private DueDateDetailResponse selectDueDateDetailByTaskId(final String taskId) {
        return dslContext
                .select(
                        DUE_DATE_DETAILS.DUE_DATE,
                        DUE_DATE_DETAILS.POSTPONE_COUNT,
                        DUE_DATE_DETAILS.MAX_POSTPONE_COUNT)
                .from(DUE_DATE_DETAILS)
                .where(DUE_DATE_DETAILS.TASK_ID.eq(taskId))
                .fetchOneInto(DueDateDetailResponse.class);
    }
}
