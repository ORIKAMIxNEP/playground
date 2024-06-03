package com.spring_boot_template.presentation.controller.task.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "削除するタスクのID",
        requiredProperties = {"taskId"})
public record DeleteTaskRequest(
        @Schema(
                        title = "タスクID",
                        type = "string",
                        minLength = 26,
                        maxLength = 26,
                        example = "0123456789ABCDEFGHJKMNPQRS")
                String taskId) {}
