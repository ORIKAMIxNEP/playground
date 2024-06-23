package com.spring_boot_template.presentation.controller.project.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "更新したタスクのステータス")
public record UpdateTaskStatusResponse(
        @Schema(title = "タスクステータス", type = "string", example = "DOING") String taskStatus) {}
