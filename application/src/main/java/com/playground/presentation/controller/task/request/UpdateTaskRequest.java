package com.playground.presentation.controller.task.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(
    title = "更新するタスク",
    requiredProperties = {"taskName", "assignedAccountIds"})
public record UpdateTaskRequest(
    @NotBlank
        @Size(min = 1, max = 20)
        @Schema(
            title = "タスク名",
            type = "string",
            minLength = 1,
            maxLength = 20,
            example = "TaskName")
        String taskName,
    @Schema(title = "割り当てられたアカウントIDリスト", type = "array", example = "[0000ABCDEFGHJKMNPQRSTVWXYZ]")
        String[] assignedAccountIds) {}
