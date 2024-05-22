package com.spring_boot_template.presentation.record.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(
    description = "取得するレコードのID",
    requiredProperties = {"recordId"})
@Builder
public record FetchRecordRequest(
    @Schema(title = "レコードID", type = "integer", format = "int64", minimum = "1", example = "1")
        long recordId) {}
