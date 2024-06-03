package com.spring_boot_template.presentation.controller.task.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "更新するタスクのデータ",
        requiredProperties = {"taskId", "column1", "column2"})
public record UpdateTaskRequest(
        @Schema(
                        title = "タスクID",
                        type = "string",
                        format = "uuid",
                        example = "01234567-89ab-cdef-0123-456789abcdef")
                String taskId,
        @Schema(
                        title = "タスクカラム1",
                        type = "integer",
                        format = "int32",
                        maximum = "127",
                        minimum = "0",
                        example = "0")
                Byte column1,
        @Schema(title = "タスクカラム2", type = "string", maxLength = 10, example = "a")
                String column2) {}
