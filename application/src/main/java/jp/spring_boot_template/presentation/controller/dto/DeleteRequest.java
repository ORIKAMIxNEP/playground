package jp.spring_boot_template.presentation.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(
    description = "削除するレコードのID",
    requiredProperties = {"recordId"})
@Builder
public record DeleteRequest(
    @Schema(title = "レコードID", type = "integer", format = "int64", minimum = "1", example = "1")
        long recordId) {}
