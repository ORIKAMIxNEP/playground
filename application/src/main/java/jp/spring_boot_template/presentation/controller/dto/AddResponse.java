package jp.spring_boot_template.presentation.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "ステータス")
@Builder
public record AddResponse(@Schema(description = "成功") boolean success) {}
