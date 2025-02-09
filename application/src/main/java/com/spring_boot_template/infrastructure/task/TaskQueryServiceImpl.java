package com.spring_boot_template.infrastructure.task;

import static com.spring_boot_template.jooq.Tables.DEADLINES;
import static com.spring_boot_template.jooq.Tables.TASKS;
import static com.spring_boot_template.jooq.Tables.TASK_ACCOUNT_ASSIGNMENTS;

import com.spring_boot_template.application.task.query.FetchTaskQueryDto;
import com.spring_boot_template.application.task.query.TaskQueryService;
import com.spring_boot_template.domain.exception.ResourceNotFoundException;
import com.spring_boot_template.domain.model.project.value.ProjectId;
import com.spring_boot_template.domain.model.task.Task;
import com.spring_boot_template.domain.model.task.value.TaskId;
import com.spring_boot_template.presentation.controller.deadline.response.FetchTaskResponseDeadlineField;
import com.spring_boot_template.presentation.controller.task.response.FetchTaskResponse;
import com.spring_boot_template.shared.constants.MessageCode;
import com.spring_boot_template.shared.module.MessageGenerator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
final class TaskQueryServiceImpl implements TaskQueryService {
    private final DSLContext dslContext;
    private final MessageGenerator messageGenerator;

    @Override
    public FetchTaskResponse findTaskByProjectIdAndTaskId(
            final ProjectId projectId, final TaskId taskId) {
        final List<FetchTaskQueryDto> fetchTaskQueryDtos =
                selectTaskByProjectIdAndTaskId(projectId.value(), taskId.value());
        if (fetchTaskQueryDtos.isEmpty()) {
            final String message =
                    messageGenerator.generateMessage(MessageCode.NOT_FOUND, Task.class);
            throw new ResourceNotFoundException(message);
        }

        final FetchTaskQueryDto fetchTaskQueryDto = fetchTaskQueryDtos.get(0);
        final String taskName = fetchTaskQueryDto.getTaskName();
        final String status = fetchTaskQueryDto.getStatus();
        final String[] assignedAccountIds =
                fetchTaskQueryDtos.stream()
                        .map(FetchTaskQueryDto::getAssignedAccountId)
                        .toArray(String[]::new);

        final FetchTaskResponseDeadlineField fetchTaskResponseDeadlineField =
                fetchTaskQueryDto
                        .getDueDate()
                        .map(
                                dueDate -> {
                                    final int postponeCount = fetchTaskQueryDto.getPostponeCount();
                                    final int maxPostponeCount =
                                            fetchTaskQueryDto.getMaxPostponeCount();
                                    return new FetchTaskResponseDeadlineField(
                                            dueDate, postponeCount, maxPostponeCount);
                                })
                        .orElse(null);

        return new FetchTaskResponse(
                taskName, status, assignedAccountIds, fetchTaskResponseDeadlineField);
    }

    private List<FetchTaskQueryDto> selectTaskByProjectIdAndTaskId(
            final String projectId, final String taskId) {
        return dslContext
                .select(
                        TASKS.TASK_NAME,
                        TASKS.STATUS,
                        TASK_ACCOUNT_ASSIGNMENTS.ASSIGNED_ACCOUNT_ID,
                        DEADLINES.DUE_DATE,
                        DEADLINES.POSTPONE_COUNT,
                        DEADLINES.MAX_POSTPONE_COUNT)
                .from(TASKS)
                .leftJoin(TASK_ACCOUNT_ASSIGNMENTS)
                .on(TASKS.TASK_ID.eq(TASK_ACCOUNT_ASSIGNMENTS.TASK_ID))
                .leftJoin(DEADLINES)
                .on(TASKS.TASK_ID.eq(DEADLINES.TASK_ID))
                .where(TASKS.PROJECT_ID.eq(projectId))
                .and(TASKS.TASK_ID.eq(taskId))
                .fetchInto(FetchTaskQueryDto.class);
    }
}
