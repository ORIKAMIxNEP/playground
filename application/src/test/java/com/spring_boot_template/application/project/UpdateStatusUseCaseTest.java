// package com.spring_boot_template.application.usecase.project;
//
// import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.doNothing;
// import static org.mockito.Mockito.doReturn;
// import static org.mockito.Mockito.doThrow;
//
// import com.spring_boot_template.domain.exception.ResourceConflictException;
// import com.spring_boot_template.domain.model.account.value.AccountId;
// import com.spring_boot_template.domain.model.project.task.Task;
// import com.spring_boot_template.domain.model.project.task.due_date.value.Date;
// import com.spring_boot_template.domain.model.project.task.due_date.value.MaxPostponeCount;
// import com.spring_boot_template.domain.model.project.task.due_date.value.PostponeCount;
// import com.spring_boot_template.domain.model.project.task.value.Status;
// import com.spring_boot_template.domain.model.project.task.value.TaskId;
// import com.spring_boot_template.domain.model.project.task.value.TaskName;
// import
// com.spring_boot_template.presentation.controller.project.task.request.UpdateTaskStatusRequest;
// import
// com.spring_boot_template.presentation.controller.project.task.response.StatusResponse;
// import org.assertj.core.api.AssertionsForClassTypes;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.springframework.test.context.junit.jupiter.SpringExtension;
//
// @SpringBootTest
// final class UpdateStatusUseCaseTest {
//    @Mock private FindTaskByTaskIdUseCase findTaskByTaskIdUseCaseMock;
//    @Mock private TaskRepository taskRepositoryMock;
//
//    @InjectMocks private AdvanceStatusUseCase updateTaskUseCase;
//
//    @Test
//    public void executeTest() {
//        doReturn(
//                new Task(
//                        new TaskId("0100ABCDEFGHJKMNPQRSTVWXYZ"),
//                        new TaskName("TaskName"),
//                        Status.UNDONE,
//                        new AccountId("0000ABCDEFGHJKMNPQRSTVWXYZ"),
//                        new Date("Date"),
//                        new PostponeCount(0),
//                        new MaxPostponeCount(10)));
//        doThrow(ResourceConflictException.class)
//                .when(findTaskByTaskIdUseCaseMock)
//                .execute(new TaskId("00000000000000000000000000"));
//        doNothing().when(taskRepositoryMock).deleteTask(any());
//        doNothing().when(taskRepositoryMock).updateTaskStatus(any());
//
//        assertThat(
//                        updateTaskUseCase.execute(
//                                new UpdateTaskStatusRequest("0100ABCDEFGHJKMNPQRSTVWXYZ")))
//                .isEqualTo(new StatusResponse("UNDONE"));
//        AssertionsForClassTypes.assertThatThrownBy(
//                        () ->
//                                updateTaskUseCase.execute(
//                                        new
// UpdateTaskStatusRequest("00000000000000000000000000")))
//                .isInstanceOf(ResourceConflictException.class);
//    }
// }
