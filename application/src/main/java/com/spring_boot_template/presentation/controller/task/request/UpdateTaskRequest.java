package com.spring_boot_template.presentation.controller.task.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        title = "更新するタスク",
        requiredProperties = {"taskName", "assignedAccountIds"})
public record UpdateTaskRequest(
        @Schema(
                        title = "タスク名",
                        type = "string",
                        minLength = 1,
                        maxLength = 10,
                        example = "TaskName")
                String taskName,
        @Schema(
                        title = "割り当てられたアカウントIDリスト",
                        type = "array",
                        example = "[0000ABCDEFGHJKMNPQRSTVWXYZ]")
                String[] assignedAccountIds) {}
