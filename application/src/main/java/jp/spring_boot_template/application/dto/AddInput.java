package jp.spring_boot_template.application.dto;

import lombok.Builder;

@Builder
public record AddInput(byte column1, String column2) {}
