package com.playground.application.task;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.doReturn;

import com.playground.application.task.impl.FetchTaskUseCaseImpl;
import com.playground.application.task.query.FetchTaskResponseConverter;
import com.playground.application.task.query.FindTaskByProjectIdAndTaskIdDto;
import com.playground.application.task.query.TaskQueryService;
import com.playground.domain.model.project.value.ProjectId;
import com.playground.domain.model.task.value.TaskId;
import com.playground.presentation.controller.deadline.response.FetchTaskResponseDeadlineField;
import com.playground.presentation.controller.project.request.ProjectIdRequest;
import com.playground.presentation.controller.task.request.TaskIdRequest;
import com.playground.presentation.controller.task.response.FetchTaskResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@RequiredArgsConstructor
@ExtendWith(MockitoExtension.class)
final class FetchTaskUseCaseImplTest {
  @Mock private TaskQueryService taskQueryService;
  @Mock private FetchTaskResponseConverter fetchTaskResponseConverter;
  @InjectMocks private FetchTaskUseCaseImpl fetchTaskUseCaseImpl;

  @Test
  void testExecute() {
    final ProjectId projectId = new ProjectId("0100ABCDEFGHJKMNPQRSTVWXYZ");
    final TaskId taskId = new TaskId("0200ABCDEFGHJKMNPQRSTVWXYZ");
    final List<FindTaskByProjectIdAndTaskIdDto> findTaskByProjectIdAndTaskIdDtos =
        generateFindTaskByProjectIdAndTaskIdDtos();
    doReturn(findTaskByProjectIdAndTaskIdDtos)
        .when(taskQueryService)
        .findTaskByProjectIdAndTaskId(projectId, taskId);

    final FetchTaskResponse expectedFetchTaskResponse = generateFetchTaskResponse();
    doReturn(expectedFetchTaskResponse)
        .when(fetchTaskResponseConverter)
        .convertFetchTaskResponse(findTaskByProjectIdAndTaskIdDtos);

    final ProjectIdRequest projectIdRequest = new ProjectIdRequest("0100ABCDEFGHJKMNPQRSTVWXYZ");
    final TaskIdRequest taskIdRequest = new TaskIdRequest("0200ABCDEFGHJKMNPQRSTVWXYZ");
    final FetchTaskResponse actualFetchTaskResponse =
        fetchTaskUseCaseImpl.execute(projectIdRequest, taskIdRequest);

    assertThat(actualFetchTaskResponse).isEqualTo(expectedFetchTaskResponse);
  }

  private List<FindTaskByProjectIdAndTaskIdDto> generateFindTaskByProjectIdAndTaskIdDtos() {
    final String taskName = "TaskName";
    final String status = "UNDONE";
    final String[] assignedAccountIds = {"0000ABCDEFGHJKMNPQRSTVWXYZ"};
    final String dueDate = "2000-01-01T00:00:00Z";
    final int postponeCount = 0;
    final int maxPostponeCount = 0;
    final FindTaskByProjectIdAndTaskIdDto findTaskByProjectIdAndTaskIdDto =
        new FindTaskByProjectIdAndTaskIdDto(
            taskName, status, assignedAccountIds, dueDate, postponeCount, maxPostponeCount);
    return List.of(findTaskByProjectIdAndTaskIdDto);
  }

  private FetchTaskResponse generateFetchTaskResponse() {
    final String taskName = "TaskName";
    final String status = "UNDONE";
    final String[] assignedAccountIds = {"0000ABCDEFGHJKMNPQRSTVWXYZ"};
    final String dueDate = "2000-01-01T00:00:00Z";
    final int postponeCount = 0;
    final int maxPostponeCount = 0;
    final FetchTaskResponseDeadlineField fetchTaskResponseDeadlineField =
        new FetchTaskResponseDeadlineField(dueDate, postponeCount, maxPostponeCount);
    return new FetchTaskResponse(
        taskName, status, assignedAccountIds, fetchTaskResponseDeadlineField);
  }
}
