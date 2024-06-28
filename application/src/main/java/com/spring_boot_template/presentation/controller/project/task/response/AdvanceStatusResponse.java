package com.spring_boot_template.presentation.controller.project.task.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title = "進めたステータス")
public record AdvanceStatusResponse(
        @Schema(title = "ステータス", type = "string", example = "DOING") String status) {}
