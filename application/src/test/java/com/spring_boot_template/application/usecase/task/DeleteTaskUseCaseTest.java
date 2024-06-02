package com.spring_boot_template.application.usecase.task;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

import com.spring_boot_template.application.usecase.task.impl.DeleteTaskUseCaseImpl;
import com.spring_boot_template.domain.exception.LogicValidationException;
import com.spring_boot_template.domain.model.task.value.TaskIdValue;
import com.spring_boot_template.infrastructure.task.TaskRdbRepository;
import com.spring_boot_template.presentation.controller.task.request.DeleteTaskRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public final class DeleteTaskUseCaseTest {
    @Mock private FindTaskByTaskIdUseCase findTaskByTaskIdUseCaseMock;
    @Mock private TaskRdbRepository recordRdbRepositoryMock;

    @InjectMocks private DeleteTaskUseCaseImpl deleteTaskUseCaseImpl;

    @Test
    public void executeTest() {
        doNothing()
                .when(findTaskByTaskIdUseCaseMock)
                .execute(new TaskIdValue("0123456789ABCDEFGHJKMNPQRS"));
        doThrow(LogicValidationException.class)
                .when(findTaskByTaskIdUseCaseMock)
                .execute(new TaskIdValue("00000000000000000000000000"));
        doNothing().when(recordRdbRepositoryMock).deleteRecord(any());

        assertThatThrownBy(
                        () ->
                                deleteTaskUseCaseImpl.execute(
                                        new DeleteTaskRequest("0123456789ABCDEFGHJKMNPQRS")))
                .doesNotThrowAnyException();
        assertThatThrownBy(
                        () ->
                                deleteTaskUseCaseImpl.execute(
                                        new DeleteTaskRequest("00000000000000000000000000")))
                .isInstanceOf(LogicValidationException.class)
                .hasMessageContaining("Record Not Found");
    }
}
