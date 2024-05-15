package jp.spring_boot_template.presentation.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(name = "レコード")
@Builder
public record FetchResponse(
    @Schema(name = "カラム1", type = "integer", format = "int32", minimun = 0, maximum = 127, nullable = true, example = 0) Byte column1, @Schema(name = "カラム2", type = "string", minLength = 1, maxLength = 10, example = "a") String column2) {}
