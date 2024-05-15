package jp.spring_boot_template.presentation.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(name = "ステータス")
@Builder
public record UpdateResponse(@Schema(name = "成功", type = "boolean", example = true) boolean success) {}
