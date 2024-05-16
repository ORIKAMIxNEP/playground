package jp.spring_boot_template.presentation.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "削除が成功したか")
@Builder
public record DeleteResponse(
    @Schema(title = "成功", type = "boolean", example = "true") boolean success) {}
