package jp.spring_boot_template.presentation.controller.dto;

import lombok.Builder;

@Builder
public record DeleteRequest(@Schema(name = "レコードID", type = "integer", format = "int64", minimun = 1, example = 1, required = true) long recordId) {}
