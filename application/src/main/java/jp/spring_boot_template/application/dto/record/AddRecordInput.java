package jp.spring_boot_template.application.dto.record;

import lombok.Builder;

@Builder
public record AddRecordInput(byte column1, String column2) {}
