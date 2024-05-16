package jp.spring_boot_template.presentation.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Schema(
    description = "追加するレコードのデータ",
    requiredProperties = {"column1", "column2"})
@Builder
public record AddRequest(
    @Schema(
            title = "レコードカラム1",
            type = "integer",
            format = "int32",
            minimum = "0",
            maximum = "127",
            example = "0")
        byte column1,
    @NotBlank
        @Size(max = 10)
        @Schema(title = "レコードカラム2", type = "string", maxLength = 10, example = "a")
        String column2) {}
