package com.spring_boot_template.presentation.record.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "hogeしたレコードのID")
@Builder
public record HogeRecordResponse(
    @Schema(title = "レコードID", type = "integer", format = "int64", minimum = "1", example = "1")
        long recordId) {}
