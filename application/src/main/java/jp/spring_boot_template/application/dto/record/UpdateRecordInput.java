package jp.spring_boot_template.application.dto.record;

import lombok.Builder;

@Builder
public record UpdateRecordInput(long recordId, byte column1, String column2) {}
