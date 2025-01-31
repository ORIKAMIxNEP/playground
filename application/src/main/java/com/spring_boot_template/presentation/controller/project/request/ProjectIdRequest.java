package com.spring_boot_template.presentation.controller.project.request;

import com.spring_boot_template.presentation.controller.common.UlidRequest;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        title = "プロジェクトID",
        requiredProperties = {"value"})
public record ProjectIdRequest(
        @Schema(
                        title = "プロジェクトID",
                        type = "string",
                        minLength = 26,
                        maxLength = 26,
                        example = "0100ABCDEFGHJKMNPQRSTVWXYZ")
                String value)
        implements UlidRequest {}
