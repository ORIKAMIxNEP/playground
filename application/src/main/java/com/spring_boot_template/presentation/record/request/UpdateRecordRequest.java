package com.spring_boot_template.presentation.record.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    description = "更新するレコードのデータ",
    requiredProperties = {"recordId", "column1", "column2"})
public record UpdateRecordRequest(
    @Schema(title = "レコードID", type = "integer", format = "int64", minimum = "1", example = "1")
        long recordId,
    @Schema(
            title = "レコードカラム1",
            type = "integer",
            format = "int32",
            maximum = "127",
            minimum = "0",
            example = "0")
        Byte column1,
    @Schema(title = "レコードカラム2", type = "string", maxLength = 10, example = "a") String column2) {}
