package com.spring_boot_template.presentation.controller.task.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "更新するタスクのID",
        requiredProperties = {"taskId"})
public record UpdateTaskStatusRequest(
        @Schema(
                        title = "タスクID",
                        type = "string",
                        minLength = 26,
                        maxLength = 26,
                        example = "1123456789ABCDEFGHJKMNPQRS")
                String taskId) {}
