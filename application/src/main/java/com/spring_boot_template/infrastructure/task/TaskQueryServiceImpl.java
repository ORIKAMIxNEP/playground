package com.spring_boot_template.infrastructure.task;

import static com.spring_boot_template.jooq.Tables.DUE_DATE_DETAILS;
import static com.spring_boot_template.jooq.Tables.TASKS;
import static com.spring_boot_template.jooq.Tables.TASK_ACCOUNT_ASSIGNMENTS;

import com.spring_boot_template.application.task.query.FetchTaskQueryDto;
import com.spring_boot_template.application.task.query.TaskQueryService;
import com.spring_boot_template.domain.exception.ResourceNotFoundException;
import com.spring_boot_template.domain.model.project.value.ProjectId;
import com.spring_boot_template.domain.model.task.value.TaskId;
import com.spring_boot_template.presentation.controller.due_date_detail.response.FetchTaskResponseDueDateDetailField;
import com.spring_boot_template.presentation.controller.task.response.FetchTaskResponse;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
final class TaskQueryServiceImpl implements TaskQueryService {
    private final DSLContext dslContext;
    private final MessageSource messageSource;

    @Override
    public FetchTaskResponse findTaskByProjectIdAndTaskId(
            final ProjectId projectId, final TaskId taskId) {
        final List<FetchTaskQueryDto> fetchTaskQueryDtos =
                selectTaskByProjectIdAndTaskId(projectId.value(), taskId.value());
        if (fetchTaskQueryDtos.isEmpty()) {
            throw new ResourceNotFoundException(
                    messageSource.getMessage("project.task.not-found", null, Locale.getDefault()));
        }

        final FetchTaskQueryDto fetchTaskQueryDto = fetchTaskQueryDtos.get(0);
        final String taskName = fetchTaskQueryDto.taskName();
        final String status = fetchTaskQueryDto.status();
        final String[] assignedAccountIds =
                fetchTaskQueryDtos.stream()
                        .map(FetchTaskQueryDto::assignedAccountId)
                        .toArray(String[]::new);

        final FetchTaskResponseDueDateDetailField fetchTaskResponseDueDateDetailField =
                Optional.ofNullable(fetchTaskQueryDto.dueDate())
                        .map(
                                dueDate -> {
                                    final int postponeCount = fetchTaskQueryDto.postponeCount();
                                    final int maxPostponeCount =
                                            fetchTaskQueryDto.maxPostponeCount();
                                    return new FetchTaskResponseDueDateDetailField(
                                            dueDate, postponeCount, maxPostponeCount);
                                })
                        .orElse(null);

        return new FetchTaskResponse(
                taskName, status, assignedAccountIds, fetchTaskResponseDueDateDetailField);
    }

    private List<FetchTaskQueryDto> selectTaskByProjectIdAndTaskId(
            final String projectId, final String taskId) {
        return dslContext
                .select(
                        TASKS.TASK_NAME,
                        TASKS.STATUS,
                        TASK_ACCOUNT_ASSIGNMENTS.ASSIGNED_ACCOUNT_ID,
                        DUE_DATE_DETAILS.DUE_DATE,
                        DUE_DATE_DETAILS.POSTPONE_COUNT,
                        DUE_DATE_DETAILS.MAX_POSTPONE_COUNT)
                .from(TASKS)
                .leftJoin(TASK_ACCOUNT_ASSIGNMENTS)
                .on(TASKS.TASK_ID.eq(TASK_ACCOUNT_ASSIGNMENTS.TASK_ID))
                .leftJoin(DUE_DATE_DETAILS)
                .on(TASKS.TASK_ID.eq(DUE_DATE_DETAILS.TASK_ID))
                .where(TASKS.PROJECT_ID.eq(projectId))
                .and(TASKS.TASK_ID.eq(taskId))
                .fetchInto(FetchTaskQueryDto.class);
    }
}
