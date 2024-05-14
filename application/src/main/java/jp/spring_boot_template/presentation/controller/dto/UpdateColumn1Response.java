package jp.spring_boot_template.presentation.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "ステータス")
public record UpdateColumn1Response(@Schema(description = "成功") boolean success) {}
