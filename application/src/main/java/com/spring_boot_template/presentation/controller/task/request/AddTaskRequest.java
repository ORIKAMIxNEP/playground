package com.spring_boot_template.presentation.controller.task.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "追加するタスクのデータ",
        requiredProperties = {"taskName", "userId", "dueDate", "maxPostponeCount"})
public record AddTaskRequest(
        @Schema(title = "タスク名", type = "string", minLength = 1, maxLength = 10, example = "task")
                String taskName,
        @Schema(
                        title = "ユーザーID",
                        type = "string",
                        minLength = 26,
                        maxLength = 26,
                        example = "0123456789ABCDEFGHJKMNPQRS")
                String userId,
        @Schema(title = "締め切り期日", type = "string", maxLength = 10, example = "a") String dueDate,
        @Schema(
                        title = "最大延期回数",
                        type = "integer",
                        format = "int32",
                        minimum = "0",
                        maximum = "10",
                        example = "0")
                byte maxPostponeCount) {}
