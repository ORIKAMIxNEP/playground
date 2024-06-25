package com.spring_boot_template.presentation.controller.project.task.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "追加するタスク",
        requiredProperties = {"taskName"})
public record AddTaskRequest(
        @Schema(
                        title = "タスク名",
                        type = "string",
                        minLength = 1,
                        maxLength = 10,
                        example = "TaskName")
                String taskName) {}
