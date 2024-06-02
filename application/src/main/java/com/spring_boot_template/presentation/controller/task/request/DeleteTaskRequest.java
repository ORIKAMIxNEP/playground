package com.spring_boot_template.presentation.controller.task.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "削除するレコードのID",
        requiredProperties = {"recordId"})
public record DeleteTaskRequest(
        @Schema(
                        title = "レコードID",
                        type = "string",
                        format = "uuid",
                        example = "01234567-89ab-cdef-0123-456789abcdef")
                String recordId) {}
