package com.spring_boot_template.presentation.controller.task.response;

import com.spring_boot_template.presentation.controller.due_date_detail.response.DueDateDetailResponse;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title = "取得したタスク")
public record FetchTaskResponse(
        @Schema(title = "タスク名", type = "string", example = "TaskName") String taskName,
        @Schema(title = "ステータス", type = "string", example = "UNDONE") String status,
        @Schema(title = "担当アカウントID", type = "array", example = "[0123456789ABCDEFGHJKMNPQRS]")
                String[] accountId,
        DueDateDetailResponse dueDateDetailResponse) {}
