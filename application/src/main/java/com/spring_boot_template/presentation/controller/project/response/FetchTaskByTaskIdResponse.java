package com.spring_boot_template.presentation.controller.project.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "取得したタスクのデータ")
public record FetchTaskResponse(
        @Schema(title = "タスク名", type = "string", example = "TaskName") String taskName,
        @Schema(title = "ステータス", type = "string", example = "UNDONE") String status,
        @Schema(title = "担当アカウントID", type = "array", example = "[0123456789ABCDEFGHJKMNPQRS]")
                String[] assignedAccountId,
        DueDateDetailResponse dueDateDetail) {}
