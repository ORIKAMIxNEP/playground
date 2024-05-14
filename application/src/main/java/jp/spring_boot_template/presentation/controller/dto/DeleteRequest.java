package jp.spring_boot_template.presentation.controller.dto;

import lombok.Builder;

@Builder
public record DeleteRequest(long recordId) {}
