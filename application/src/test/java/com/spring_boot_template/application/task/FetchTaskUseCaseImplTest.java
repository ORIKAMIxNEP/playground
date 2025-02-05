package com.spring_boot_template.application.task;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.doReturn;

import com.spring_boot_template.application.task.impl.FetchTaskUseCaseImpl;
import com.spring_boot_template.application.task.query.TaskQueryService;
import com.spring_boot_template.domain.model.project.value.ProjectId;
import com.spring_boot_template.domain.model.task.value.TaskId;
import com.spring_boot_template.presentation.controller.due_date_detail.response.FetchTaskResponseDueDateDetailField;
import com.spring_boot_template.presentation.controller.project.request.ProjectIdRequest;
import com.spring_boot_template.presentation.controller.task.request.TaskIdRequest;
import com.spring_boot_template.presentation.controller.task.response.FetchTaskResponse;
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
    @InjectMocks private FetchTaskUseCaseImpl fetchTaskUseCaseImpl;

    @Test
    void testExecute() {
        final ProjectId projectId = new ProjectId("0100ABCDEFGHJKMNPQRSTVWXYZ");
        final TaskId taskId = new TaskId("0200ABCDEFGHJKMNPQRSTVWXYZ");
        final FetchTaskResponse expectedFetchTaskResponse = generateFetchTaskResponse();
        doReturn(expectedFetchTaskResponse)
                .when(taskQueryService)
                .findTaskByProjectIdAndTaskId(projectId, taskId);

        final ProjectIdRequest projectIdRequest =
                new ProjectIdRequest("0100ABCDEFGHJKMNPQRSTVWXYZ");
        final TaskIdRequest taskIdRequest = new TaskIdRequest("0200ABCDEFGHJKMNPQRSTVWXYZ");
        final FetchTaskResponse actualFetchTaskResponse =
                fetchTaskUseCaseImpl.execute(projectIdRequest, taskIdRequest);

        assertThat(actualFetchTaskResponse).isEqualTo(expectedFetchTaskResponse);
    }

    private FetchTaskResponse generateFetchTaskResponse() {
        final String taskName = "TaskName";
        final String status = "UNDONE";
        final String[] assignedAccountIds = {"0000ABCDEFGHJKMNPQRSTVWXYZ"};
        final String dueDate = "2000-01-01T00:00:00Z";
        final int postponeCount = 0;
        final int maxPostponeCount = 0;
        final FetchTaskResponseDueDateDetailField fetchTaskResponseDueDateDetailField =
                new FetchTaskResponseDueDateDetailField(dueDate, postponeCount, maxPostponeCount);
        return new FetchTaskResponse(
                taskName, status, assignedAccountIds, fetchTaskResponseDueDateDetailField);
    }
}
