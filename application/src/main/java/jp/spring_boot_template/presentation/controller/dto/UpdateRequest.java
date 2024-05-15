package jp.spring_boot_template.presentation.controller.dto;

import lombok.Builder;

@Schema(name = "レコード")
@Builder
public record UpdateRequest(@Schema(name = "レコードID", type = "integer", format = "int64", minimun = 1, example = 1, required = true) long recordId, @Schema(name = "カラム1", type = "integer", format = "int32", minimun = 0, maximum = 127, example = 0, required = true) short column1, @NotBlank @Size(max = 10) @Schema(name = "カラム2", type = "string", minLength = 1, maxLength = 10, example = "a", required = true) String column2) {}
