package jp.spring_boot_template.presentation.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(
    description = "カラム1を更新するレコードのデータ",
    requiredProperties = {"recordId", "column1"})
@Builder
public record UpdateRecordColumn1Request(
    @Schema(title = "レコードID", type = "integer", format = "int64", minimum = "1", example = "1")
        long recordId,
    @Schema(
            title = "レコードカラム1",
            type = "integer",
            format = "int32",
            maximum = "127",
            minimum = "0",
            example = "0")
        byte column1) {}
