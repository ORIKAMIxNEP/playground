package com.spring_boot_template.application.usecase.project;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import com.spring_boot_template.domain.exception.ValidationException;
import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.task.Task;
import com.spring_boot_template.domain.model.task.value.DueDate;
import com.spring_boot_template.domain.model.task.value.MaxPostponeCount;
import com.spring_boot_template.domain.model.task.value.PostponeCount;
import com.spring_boot_template.domain.model.task.value.Status;
import com.spring_boot_template.domain.model.task.value.TaskId;
import com.spring_boot_template.domain.model.task.value.TaskName;
import com.spring_boot_template.presentation.controller.project.response.FetchTaskByTaskIdResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
final class FetchTaskByTaskIdUseCaseTest {
    @Mock private FindTaskByTaskIdUseCase findTaskByTaskIdUseCaseMock;

    @InjectMocks private FetchTaskByTaskIdUseCase fetchTaskByTaskIdUseCase;

    @Test
    public void executeTest() {
        doReturn(
                        new Task(
                                new TaskId("1123456789ABCDEFGHJKMNPQRS"),
                                new TaskName("TaskName"),
                                Status.UNDONE,
                                new AccountId("0123456789ABCDEFGHJKMNPQRS"),
                                new DueDate("DueDate"),
                                new PostponeCount(0),
                                new MaxPostponeCount(10)))
                .when(findTaskByTaskIdUseCaseMock)
                .execute(new TaskId("1123456789ABCDEFGHJKMNPQRS"));
        doThrow(ValidationException.class)
                .when(findTaskByTaskIdUseCaseMock)
                .execute(new TaskId("00000000000000000000000000"));

        assertThat(fetchTaskByTaskIdUseCase.execute("1123456789ABCDEFGHJKMNPQRS"))
                .isEqualTo(
                        new FetchTaskByTaskIdResponse(
                                "TaskName",
                                "UNDONE",
                                "0123456789ABCDEFGHJKMNPQRS",
                                "DueDate",
                                0,
                                10));
        assertThatThrownBy(() -> fetchTaskByTaskIdUseCase.execute("00000000000000000000000000"))
                .isInstanceOf(ValidationException.class);
    }
}
