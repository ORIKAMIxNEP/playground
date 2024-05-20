package jp.spring_boot_template.application.dto.record;

import lombok.Builder;

@Builder
public record DeleteRecordOutput(boolean success) {}
