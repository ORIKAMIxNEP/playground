package com.spring_boot_template.application.usecase.task;

import com.spring_boot_template.domain.exception.ValidationException;
import com.spring_boot_template.domain.model.task.TaskEntity;
import com.spring_boot_template.domain.model.task.TaskRepository;
import com.spring_boot_template.domain.model.task.value.DueDateValue;
import com.spring_boot_template.domain.model.task.value.MaxPostponeCountValue;
import com.spring_boot_template.domain.model.task.value.PostponeCountValue;
import com.spring_boot_template.domain.model.task.value.TaskIdValue;
import com.spring_boot_template.domain.model.task.value.TaskNameValue;
import com.spring_boot_template.domain.model.task.value.TaskStatusType;
import com.spring_boot_template.domain.model.user.value.UserIdValue;
import com.spring_boot_template.presentation.controller.task.request.DeleteTaskRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@ExtendWith(SpringExtension.class)
final class DeleteTaskUseCaseTest {
    @Mock private FindTaskByTaskIdUseCase findTaskByTaskIdUseCaseMock;
    @Mock private TaskRepository taskRepositoryMock;

    @InjectMocks private DeleteTaskUseCase deleteTaskUseCase;

    @Test
    public void executeTest() {
        doReturn(
                new TaskEntity(
                        new TaskIdValue("1123456789ABCDEFGHJKMNPQRS"),
                        new TaskNameValue("TaskName"),
                        TaskStatusType.UNDONE,
                        new UserIdValue("0123456789ABCDEFGHJKMNPQRS"),
                        new DueDateValue("DueDate"),
                        new PostponeCountValue(0),
                        new MaxPostponeCountValue(10)));
        doThrow(ValidationException.class)
                .when(findTaskByTaskIdUseCaseMock)
                .execute(new TaskIdValue("00000000000000000000000000"));
        doNothing().when(taskRepositoryMock).deleteTask(any());

        assertThatThrownBy(
                        () ->
                                deleteTaskUseCase.execute(
                                        new DeleteTaskRequest("1123456789ABCDEFGHJKMNPQRS")))
                .doesNotThrowAnyException();
        assertThatThrownBy(
                        () ->
                                deleteTaskUseCase.execute(
                                        new DeleteTaskRequest("00000000000000000000000000")))
                .isInstanceOf(ValidationException.class);
    }
}
