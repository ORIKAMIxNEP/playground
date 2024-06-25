package com.spring_boot_template.presentation.controller.project.task.due_date_detail.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "取得した詳細締め切り期日")
public record FetchDueDateDetailResponse(
        @Schema(title = "締め切り期日", example = "Date") String dueDate,
        @Schema(title = "延期回数", type = "integer", format = "int32", example = "0")
                int postponeCount,
        @Schema(title = "最大延期回数", type = "integer", format = "int32", example = "10")
                int maxPostponeCount) {}
