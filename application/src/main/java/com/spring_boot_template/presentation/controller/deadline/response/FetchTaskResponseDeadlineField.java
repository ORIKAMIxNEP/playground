package com.spring_boot_template.presentation.controller.deadline.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title = "取得したタスクの締め切り")
public record FetchTaskResponseDeadlineField(
        @Schema(
                        title = "期日",
                        type = "string",
                        format = "date-time",
                        example = "2000-01-01T00:00:00")
                String dueDate,
        @Schema(title = "延期回数", type = "integer", format = "int32", example = "0")
                int postponeCount,
        @Schema(title = "最大延期回数", type = "integer", format = "int32", example = "10")
                int maxPostponeCount) {}
