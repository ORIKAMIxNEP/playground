package com.spring_boot_template.presentation.controller.due_date_detail.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        title = "設定する詳細締め切り期日",
        requiredProperties = {"dueDate, maxPostponeCount"})
public record SetDueDateDetailRequest(
        @Schema(
                        title = "締め切り期日",
                        type = "string",
                        format = "date-time",
                        example = "2000-01-01T00:00:00")
                String dueDate,
        @Schema(
                        title = "最大延期回数",
                        type = "integer",
                        format = "int32",
                        minimum = "0",
                        maximum = "10",
                        example = "0")
                int maxPostponeCount) {}
