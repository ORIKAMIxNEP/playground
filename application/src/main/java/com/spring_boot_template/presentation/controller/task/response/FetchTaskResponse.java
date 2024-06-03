package com.spring_boot_template.presentation.controller.task.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "取得したタスクのデータ")
public record FetchTaskResponse(
        @Schema(
                        title = "タスクカラム1",
                        type = "integer",
                        format = "int32",
                        maximum = "127",
                        minimum = "0",
                        nullable = true,
                        example = "0")
                Byte column1,
        @Schema(
                        title = "タスクカラム2",
                        type = "string",
                        maxLength = 10,
                        minLength = 1,
                        nullable = true,
                        example = "a")
                String column2) {}
