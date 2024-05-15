package jp.spring_boot_template.application.dto;

import lombok.Builder;

@Builder
public record FetchOutput(boolean success, Byte column1, String column2) {}
