package com.spring_boot_template.presentation.record.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "取得したレコードのデータ")
@Builder
public record FetchRecordResponse(
    @Schema(title = "成功したか", type = "boolean", example = "true") boolean isSuccessful,
    @Schema(
            title = "レコードカラム1",
            type = "integer",
            format = "int32",
            maximum = "127",
            minimum = "0",
            nullable = true,
            example = "0")
        Byte column1,
    @Schema(
            title = "レコードカラム2",
            type = "string",
            maxLength = 10,
            minLength = 1,
            nullable = true,
            example = "a")
        String column2) {}
