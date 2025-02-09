package com.playground.infrastructure.task;

import com.playground.domain.model.task.value.Status;
import com.playground.domain.model.task.value.TaskId;
import com.playground.domain.model.task.value.TaskName;

public record TaskDto(TaskId taskId, TaskName taskName, Status status) {}
