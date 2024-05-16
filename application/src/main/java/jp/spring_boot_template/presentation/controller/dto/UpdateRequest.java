package jp.spring_boot_template.presentation.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Schema(
    description = "更新するレコードのデータ",
    requiredProperties = {"recordId", "column1", "column2"})
@Builder
public record UpdateRequest(
    @Schema(title = "レコードID", type = "integer", format = "int64", minimum = "1", example = "1")
        long recordId,
    @Schema(
            title = "レコードカラム1",
            type = "integer",
            format = "int32",
            maximum = "127",
            minimum = "0",
            example = "0")
        byte column1,
    @NotBlank
        @Size(max = 10)
        @Schema(title = "レコードカラム2", type = "string", maxLength = 10, example = "a")
        String column2) {}
