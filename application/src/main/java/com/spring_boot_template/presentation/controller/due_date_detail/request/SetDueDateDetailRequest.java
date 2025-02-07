package com.spring_boot_template.presentation.controller.due_date_detail.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Schema(
        title = "設定する詳細締め切り期日",
        requiredProperties = {"dueDate, maxPostponeCount"})
public record SetDueDateDetailRequest(
        @NotBlank
                @Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}")
                @Schema(
                        title = "締め切り期日",
                        type = "string",
                        format = "date-time",
                        example = "2000-01-01T00:00:00")
                String dueDate,
        @NotBlank
                @Min(value = 0)
                @Max(value = 10)
                @Schema(
                        title = "最大延期回数",
                        type = "integer",
                        format = "int32",
                        minimum = "0",
                        maximum = "10",
                        example = "0")
                int maxPostponeCount) {}
