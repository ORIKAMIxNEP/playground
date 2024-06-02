package com.spring_boot_template.application.usecase.task;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.doReturn;

import com.spring_boot_template.application.usecase.task.impl.FetchTaskByTaskIdUseCaseImpl;
import com.spring_boot_template.domain.exception.LogicValidationException;
import com.spring_boot_template.domain.model.task.TaskEntity;
import com.spring_boot_template.domain.model.task.value.AssignedUserIdValue;
import com.spring_boot_template.domain.model.task.value.TaskIdValue;
import com.spring_boot_template.domain.model.task.value.TaskNameValue;
import com.spring_boot_template.infrastructure.task.TaskRdbRepository;
import com.spring_boot_template.presentation.controller.task.response.FetchTaskResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public final class FetchTaskByTaskIdUseCaseTest {
    @Mock private TaskRdbRepository recordRdbRepositoryMock;

    @InjectMocks private FetchTaskByTaskIdUseCaseImpl fetchTaskByTaskIdUseCaseImpl;

    @Test
    public void executeTest() {
        doReturn(
                        new TaskEntity(
                                new TaskIdValue("0123456789ABCDEFGHJKMNPQRS"),
                                new AssignedUserIdValue((byte) 0),
                                new TaskNameValue("a")))
                .when(recordRdbRepositoryMock)
                .fetchTaskByTaskId(new TaskIdValue("0123456789ABCDEFGHJKMNPQRS"));
        doReturn(null)
                .when(recordRdbRepositoryMock)
                .fetchTaskByTaskId(new TaskIdValue("00000000000000000000000000"));

        assertThat(fetchTaskByTaskIdUseCaseImpl.execute("0123456789ABCDEFGHJKMNPQRS"))
                .isEqualTo(new FetchTaskResponse((byte) 0, "a"));
        assertThatThrownBy(
                        () -> {
                            fetchTaskByTaskIdUseCaseImpl.execute("00000000000000000000000000");
                        })
                .isInstanceOf(LogicValidationException.class)
                .hasMessageContaining("Record Not Found");
    }
}
