package com.playground.presentation.controller.task.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(
    title = "追加するタスク",
    requiredProperties = {"taskName"})
public record AddTaskRequest(
    @NotBlank
        @Size(min = 1, max = 20)
        @Schema(
            title = "タスク名",
            type = "string",
            minLength = 1,
            maxLength = 20,
            example = "TaskName")
        String taskName) {}
