package com.spring_boot_template.presentation.controller.project.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "プロジェクト")
public record ProjectResponse(
        @Schema(title = "プロジェクトID", type = "string", example = "1123456789ABCDEFGHJKMNPQRS")
                String projectId,
        @Schema(title = "プロジェクト名", type = "string", example = "ProjectName") String projectName) {}
