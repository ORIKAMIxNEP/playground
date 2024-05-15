package jp.spring_boot_template.presentation.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(name = "レコード")
@Builder
public record UpdateColumn1Request(@Schema(name = "レコードID", type = "integer", format = "int64", minimun = 0, example = 1, required = true) long recordId, @Schema(name = "カラム1", type = "integer", format = "int32", minimun = 0, maximum = 127, example = 0, required = true) byte column1) {}
