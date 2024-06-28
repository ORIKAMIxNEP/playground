package com.spring_boot_template.presentation.controller.project.task.due_date_detail.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title = "取得した詳細締め切り期日")
public record FetchDueDateDetailResponse(
        @Schema(
                        title = "締め切り期日",
                        type = "string",
                        format = "date-time",
                        example = "2000-01-01T00:00:00Z")
                String dueDate,
        @Schema(title = "延期回数", type = "integer", format = "int32", example = "0")
                int postponeCount,
        @Schema(title = "最大延期回数", type = "integer", format = "int32", example = "10")
                int maxPostponeCount) {}
