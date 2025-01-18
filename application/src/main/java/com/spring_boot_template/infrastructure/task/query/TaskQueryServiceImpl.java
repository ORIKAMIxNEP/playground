package com.spring_boot_template.infrastructure.task.query;

import static com.spring_boot_template.jooq.Tables.TASKS;
import static com.spring_boot_template.jooq.Tables.TASK_ACCOUNT_ASSIGNMENTS;

import com.spring_boot_template.application.due_date_detail.query.DueDateDetailQueryDto;
import com.spring_boot_template.application.due_date_detail.query.DueDateDetailQueryService;
import com.spring_boot_template.application.task.query.TaskQueryDto;
import com.spring_boot_template.application.task.query.TaskQueryService;
import com.spring_boot_template.domain.exception.ResourceNotFoundException;
import com.spring_boot_template.domain.model.project.value.ProjectId;
import com.spring_boot_template.domain.model.task.value.TaskId;
import com.spring_boot_template.presentation.controller.due_date_detail.response.DueDateDetailResponse;
import com.spring_boot_template.presentation.controller.task.response.FetchTaskResponse;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record3;
import org.jooq.Result;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
@RequiredArgsConstructor
final class TaskQueryServiceImpl implements TaskQueryService {
    private final DSLContext dslContext;

    @Override
    public FetchTaskResponse findTaskByProjectIdAndTaskId(
            final ProjectId projectId, final TaskId taskId) {
        final Result<Record3<String, String, String>> taskRecords =
                dslContext
                        .select(TASKS.TASK_NAME, TASKS.STATUS, TASK_ACCOUNT_ASSIGNMENTS.ACCOUNT_ID)
                        .from(TASKS)
                        .leftJoin(TASK_ACCOUNT_ASSIGNMENTS)
                        .on(TASKS.TASK_ID.eq(TASK_ACCOUNT_ASSIGNMENTS.TASK_ID))
                        .where(TASKS.PROJECT_ID.eq(projectId.value()))
                        .and(TASKS.TASK_ID.eq(taskId.value()))
                        .fetch();

        if (CollectionUtils.isEmpty(taskRecords)) {
            throw new ResourceNotFoundException("Task is not found");
        }

        final Record taskRecord = taskRecords.get(0);
        final String taskName = taskRecord.get(TASKS.TASK_NAME);
        final String status = taskRecord.get(TASKS.STATUS);
        final List<String> accountIds = taskRecords.getValues(TASK_ACCOUNT_ASSIGNMENTS.ACCOUNT_ID);

        return null;
    }

    private final TaskQueryMapper taskQueryMapper;
    private final DueDateDetailQueryService dueDateDetailQueryService;

    //    @Override
    public FetchTaskResponse findTaskByProjectIdAndTaskId2(
            final ProjectId projectId, final TaskId taskId) {
        final TaskQueryDto taskQueryDto =
                taskQueryMapper.selectTaskByProjectIdAndTaskId(projectId, taskId);

        if (Objects.isNull(taskQueryDto)) {
            throw new ResourceNotFoundException("Task is not found");
        }

        final Optional<DueDateDetailQueryDto> optionalDueDateDetailDto =
                dueDateDetailQueryService.findDueDateDetailByTaskId(taskId);
        final DueDateDetailQueryDto dueDateDetailQueryDto = optionalDueDateDetailDto.orElse(null);
        taskQueryDto.setDueDateDetailQueryDto(dueDateDetailQueryDto);

        final String taskName = taskQueryDto.getTaskName();
        final String status = taskQueryDto.getStatus();
        final String[] accountIds = taskQueryDto.getAccountIds();
        final DueDateDetailResponse dueDateDetailResponse =
                optionalDueDateDetailDto
                        .map(
                                dueDateDetailDto -> {
                                    final String dueDate =
                                            dueDateDetailDto
                                                    .getDueDate()
                                                    .toString(); // .toLocalDateTime().toString();
                                    final int postponeCount = dueDateDetailDto.getPostponeCount();
                                    final int maxPostponeCount =
                                            dueDateDetailDto.getMaxPostponeCount();

                                    return new DueDateDetailResponse(
                                            dueDate, postponeCount, maxPostponeCount);
                                })
                        .orElse(null);

        return new FetchTaskResponse(taskName, status, accountIds, dueDateDetailResponse);
    }
}
