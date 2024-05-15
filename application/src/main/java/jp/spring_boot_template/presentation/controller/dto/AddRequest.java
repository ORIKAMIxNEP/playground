package jp.spring_boot_template.presentation.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Schema(description = "レコード")
@Builder
public record AddRequest(@Schema(description = "カラム1", type = "integer", format = "int32", minimun = 0, maximum = 127, example = 0) byte column1, @NotBlank @Size(max = 10) @Schema(description = "カラム2", type = "string", minLength = 1, maxLength = 10, example = "a") String column2) {}
