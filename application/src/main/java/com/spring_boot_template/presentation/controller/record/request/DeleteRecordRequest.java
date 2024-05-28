package com.spring_boot_template.presentation.controller.record.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "削除するレコードのID",
        requiredProperties = {"recordId"})
public record DeleteRecordRequest(
        @Schema(title = "レコードID", type = "integer", format = "int64", minimum = "1", example = "1")
                long recordId) {}
