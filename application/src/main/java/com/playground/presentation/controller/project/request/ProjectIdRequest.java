package com.playground.presentation.controller.project.request;

import com.playground.presentation.controller.common.UlidRequest;
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
