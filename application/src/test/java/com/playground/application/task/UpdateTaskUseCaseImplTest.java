package com.playground.application.task;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

import com.playground.application.task.impl.UpdateTaskUseCaseImpl;
import com.playground.domain.model.account.value.AccountId;
import com.playground.domain.model.deadline.Deadline;
import com.playground.domain.model.deadline.value.DueDate;
import com.playground.domain.model.deadline.value.MaxPostponeCount;
import com.playground.domain.model.deadline.value.PostponeCount;
import com.playground.domain.model.project.Project;
import com.playground.domain.model.project.ProjectRepository;
import com.playground.domain.model.project.value.ProjectId;
import com.playground.domain.model.project.value.ProjectName;
import com.playground.domain.model.task.Task;
import com.playground.domain.model.task.value.Status;
import com.playground.domain.model.task.value.TaskId;
import com.playground.domain.model.task.value.TaskName;
import com.playground.presentation.controller.project.request.ProjectIdRequest;
import com.playground.presentation.controller.task.request.TaskIdRequest;
import com.playground.presentation.controller.task.request.UpdateTaskRequest;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@RequiredArgsConstructor
@ExtendWith(MockitoExtension.class)
final class UpdateTaskUseCaseImplTest {
  @Mock private ProjectRepository projectRepository;
  @InjectMocks private UpdateTaskUseCaseImpl updateTaskUseCaseImpl;

  @Test
  void testExecute() {
    final ProjectId projectId = new ProjectId("0100ABCDEFGHJKMNPQRSTVWXYZ");
    final Project project = generateProject();
    doReturn(project).when(projectRepository).findProjectByProjectId(projectId);
    doNothing().when(projectRepository).saveProject(project);

    final ProjectIdRequest projectIdRequest = new ProjectIdRequest("0100ABCDEFGHJKMNPQRSTVWXYZ");
    final TaskIdRequest taskIdRequest = new TaskIdRequest("0200ABCDEFGHJKMNPQRSTVWXYZ");
    final UpdateTaskRequest updateTaskRequest = generateUpdateTaskRequest();
    updateTaskUseCaseImpl.execute(projectIdRequest, taskIdRequest, updateTaskRequest);
  }

  private Project generateProject() {
    final ProjectId projectId = new ProjectId("0100ABCDEFGHJKMNPQRSTVWXYZ");
    final ProjectName projectName = new ProjectName("ProjectName");
    final Set<AccountId> participatingAccountIds = new HashSet<>();
    participatingAccountIds.add(new AccountId("0100ABCDEFGHJKMNPQRSTVWXYZ"));
    final TaskId taskId = new TaskId("0200ABCDEFGHJKMNPQRSTVWXYZ");
    final TaskName taskName = new TaskName("TaskName");
    final Status status = Status.UNDONE;
    final Set<AccountId> assignedAccountIds = new HashSet<>();
    assignedAccountIds.add(new AccountId("0100ABCDEFGHJKMNPQRSTVWXYZ"));
    final DueDate dueDate = new DueDate(LocalDateTime.of(2000, 1, 1, 0, 0, 0));
    final PostponeCount postponeCount = new PostponeCount(0);
    final MaxPostponeCount maxPostponeCount = new MaxPostponeCount(0);
    final Deadline deadline =
        Deadline.reconstructDeadline(dueDate, postponeCount, maxPostponeCount);
    final LinkedHashSet<Task> tasks = new LinkedHashSet<>();
    tasks.add(Task.reconstructTask(taskId, taskName, status, assignedAccountIds, deadline));
    return Project.reconstructProject(projectId, projectName, participatingAccountIds, tasks);
  }

  private UpdateTaskRequest generateUpdateTaskRequest() {
    final String taskName = "UpdatedTaskName";
    final String[] assignedAccountIds = {"0100ABCDEFGHJKMNPQRSTVWXYZ"};
    return new UpdateTaskRequest(taskName, assignedAccountIds);
  }
}
