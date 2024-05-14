package jp.spring_boot_template.application.dto;

import lombok.Builder;

@Builder
public record DeleteInput(long recordId) {}
