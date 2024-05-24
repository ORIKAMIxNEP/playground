package com.spring_boot_template.presentation.record.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "更新が成功したか")
@Builder
public record UpdateRecordResponse(
    @Schema(title = "成功したか", type = "boolean", example = "true") boolean isSuccessful) {}
