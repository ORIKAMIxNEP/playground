// package com.spring_boot_template.application.usecase.project;
//
// import static org.assertj.core.api.Assertions.assertThatThrownBy;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.doNothing;
// import static org.mockito.Mockito.doReturn;
// import static org.mockito.Mockito.doThrow;
//
// import com.spring_boot_template.domain.exception.ValidationException;
// import com.spring_boot_template.domain.model.account.value.AccountId;
// import com.spring_boot_template.domain.model.project.task.Task;
// import com.spring_boot_template.domain.model.project.task.due_date.value.Date;
// import com.spring_boot_template.domain.model.project.task.due_date.value.MaxPostponeCount;
// import com.spring_boot_template.domain.model.project.task.due_date.value.PostponeCount;
// import com.spring_boot_template.domain.model.project.task.value.Status;
// import com.spring_boot_template.domain.model.project.task.value.TaskId;
// import com.spring_boot_template.domain.model.project.task.value.TaskName;
// import com.spring_boot_template.presentation.controller.project.request.DeleteTaskRequest;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.springframework.test.context.junit.jupiter.SpringExtension;
//
// @ExtendWith(SpringExtension.class)
// final class DeleteTaskUseCaseTest {
//    @Mock private FindTaskByTaskIdUseCase findTaskByTaskIdUseCaseMock;
//    @Mock private TaskRepository taskRepositoryMock;
//
//    @InjectMocks private DeleteTaskUseCase deleteTaskUseCase;
//
//    @Test
//    public void executeTest() {
//        doReturn(
//                new Task(
//                        new TaskId("1123456789ABCDEFGHJKMNPQRS"),
//                        new TaskName("TaskName"),
//                        Status.UNDONE,
//                        new AccountId("0123456789ABCDEFGHJKMNPQRS"),
//                        new Date("Date"),
//                        new PostponeCount(0),
//                        new MaxPostponeCount(10)));
//        doThrow(ValidationException.class)
//                .when(findTaskByTaskIdUseCaseMock)
//                .execute(new TaskId("00000000000000000000000000"));
//        doNothing().when(taskRepositoryMock).deleteTask(any());
//
//        assertThatThrownBy(
//                        () ->
//                                deleteTaskUseCase.execute(
//                                        new DeleteTaskRequest("1123456789ABCDEFGHJKMNPQRS")))
//                .doesNotThrowAnyException();
//        assertThatThrownBy(
//                        () ->
//                                deleteTaskUseCase.execute(
//                                        new DeleteTaskRequest("00000000000000000000000000")))
//                .isInstanceOf(ValidationException.class);
//    }
// }
