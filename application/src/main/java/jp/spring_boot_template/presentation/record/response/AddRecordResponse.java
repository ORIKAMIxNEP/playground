package com.spring_boot_template.presentation.record.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "追加が成功したか")
@Builder
public record AddRecordResponse(
    @Schema(title = "成功", type = "boolean", example = "true") boolean success) {}
