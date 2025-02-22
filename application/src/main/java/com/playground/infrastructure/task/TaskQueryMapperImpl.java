package com.playground.infrastructure.task;

import static com.playground.jooq.Tables.DEADLINES;
import static com.playground.jooq.Tables.TASKS;
import static com.playground.jooq.Tables.TASK_ACCOUNT_ASSIGNMENTS;

import com.playground.application.task.query.FindTaskByProjectIdAndTaskIdDto;
import com.playground.domain.model.project.value.ProjectId;
import com.playground.domain.model.task.value.TaskId;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
final class TaskQueryMapperImpl implements TaskQueryMapper {
  private final DSLContext dslContext;

  @Override
  public List<FindTaskByProjectIdAndTaskIdDto> selectTaskByProjectIdAndTaskId(
      final ProjectId projectId, final TaskId taskId) {
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
        .where(TASKS.PROJECT_ID.eq(projectId.value()))
        .and(TASKS.TASK_ID.eq(taskId.value()))
        .fetchInto(FindTaskByProjectIdAndTaskIdDto.class);
  }
}
