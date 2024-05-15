package jp.spring_boot_template.presentation.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@SchemaU(name = "レコードID")
@Builder
public record DeleteRequest(@Schema(name = "レコードID", type = "integer", format = "int64", minimun = 1, example = 1, required = true) long recordId) {}
