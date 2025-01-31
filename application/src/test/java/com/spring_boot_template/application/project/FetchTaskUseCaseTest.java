// package com.spring_boot_template.application.usecase.project;
//
// import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
// import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
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
// import com.spring_boot_template.presentation.controller.project.task.response.TaskResponse;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.springframework.test.context.junit.jupiter.SpringExtension;
//
// @SpringBootTest
// final class FetchTaskUseCaseTest {
//    @Mock private FindTaskByTaskIdUseCase findTaskByTaskIdUseCaseMock;
//
//    @InjectMocks private FetchTaskUseCase fetchTaskUseCase;
//
//    @Test
//    public void executeTest() {
//        doReturn(
//                        new Task(
//                                new TaskId("0100ABCDEFGHJKMNPQRSTVWXYZ"),
//                                new TaskName("TaskName"),
//                                Status.UNDONE,
//                                new AccountId("0000ABCDEFGHJKMNPQRSTVWXYZ"),
//                                new Date("Date"),
//                                new PostponeCount(0),
//                                new MaxPostponeCount(10)))
//                .when(findTaskByTaskIdUseCaseMock)
//                .execute(new TaskId("0100ABCDEFGHJKMNPQRSTVWXYZ"));
//        doThrow(ResourceConflictException.class)
//                .when(findTaskByTaskIdUseCaseMock)
//                .execute(new TaskId("00000000000000000000000000"));
//
//        assertThat(fetchTaskUseCase.execute("0100ABCDEFGHJKMNPQRSTVWXYZ"))
//                .isEqualTo(
//                        new TaskResponse(
//                                "TaskName", "UNDONE", "0000ABCDEFGHJKMNPQRSTVWXYZ", "Date", 0,
// 10));
//        assertThatThrownBy(() -> fetchTaskUseCase.execute("00000000000000000000000000"))
//                .isInstanceOf(ResourceConflictException.class);
//    }
// }
