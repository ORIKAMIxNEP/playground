package com.spring_boot_template.presentation.controller.record.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "追加するレコードのデータ",
        requiredProperties = {"column1", "column2"})
public record AddRecordRequest(
        @Schema(
                        title = "レコードカラム1",
                        type = "integer",
                        format = "int32",
                        minimum = "0",
                        maximum = "127",
                        example = "0")
                byte column1,
        @Schema(title = "レコードカラム2", type = "string", maxLength = 10, example = "a")
                String column2) {}
