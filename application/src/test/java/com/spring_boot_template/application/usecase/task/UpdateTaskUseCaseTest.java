package com.spring_boot_template.application.usecase.task;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

import com.spring_boot_template.application.usecase.task.impl.UpdateTaskUseCaseImpl;
import com.spring_boot_template.domain.exception.LogicValidationException;
import com.spring_boot_template.domain.model.task.value.TaskIdValue;
import com.spring_boot_template.infrastructure.task.TaskRdbRepository;
import com.spring_boot_template.presentation.controller.task.request.UpdateTaskRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public final class UpdateTaskUseCaseTest {
    @Mock private FindTaskByTaskIdUseCase findTaskByTaskIdUseCaseMock;
    @Mock private TaskRdbRepository recordRdbRepositoryMock;

    @InjectMocks private UpdateTaskUseCaseImpl updateTaskUseCaseImpl;

    @Test
    public void executeTest() {
        doNothing()
                .when(findTaskByTaskIdUseCaseMock)
                .execute(new TaskIdValue("0123456789ABCDEFGHJKMNPQRS"));
        doThrow(LogicValidationException.class)
                .when(findTaskByTaskIdUseCaseMock)
                .execute(new TaskIdValue("00000000000000000000000000"));
        doNothing().when(recordRdbRepositoryMock).updateTaskName(any());

        assertThatThrownBy(
                        () ->
                                updateTaskUseCaseImpl.execute(
                                        new UpdateTaskRequest(
                                                "0123456789ABCDEFGHJKMNPQRS", (byte) 0, "a")))
                .doesNotThrowAnyException();
        assertThatThrownBy(
                        () ->
                                updateTaskUseCaseImpl.execute(
                                        new UpdateTaskRequest(
                                                "00000000000000000000000000", (byte) 0, "a")))
                .isInstanceOf(LogicValidationException.class)
                .hasMessageContaining("Record Not Found");
    }
}
