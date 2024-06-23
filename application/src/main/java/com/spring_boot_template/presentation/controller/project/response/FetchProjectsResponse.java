package com.spring_boot_template.presentation.controller.project.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "プロジェクトリスト")
public record FetchProjectsResponse(
        @Schema(title = "プロジェクトリスト", type = "array") List<ProjectResponse> projects){}
