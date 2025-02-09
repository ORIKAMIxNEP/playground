package com.playground.presentation.controller.task.request;

import com.playground.presentation.controller.common.UlidRequest;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    title = "タスクID",
    requiredProperties = {"value"})
public record TaskIdRequest(
    @Schema(
            title = "タスクID",
            type = "string",
            minLength = 26,
            maxLength = 26,
            example = "0100ABCDEFGHJKMNPQRSTVWXYZ")
        String value)
    implements UlidRequest {}
