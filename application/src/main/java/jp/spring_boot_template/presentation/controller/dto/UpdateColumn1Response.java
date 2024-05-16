package jp.spring_boot_template.presentation.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "レコードカラム1の更新が成功したか")
@Builder
public record UpdateColumn1Response(
    @Schema(title = "成功", type = "boolean", example = "true") boolean success) {}
