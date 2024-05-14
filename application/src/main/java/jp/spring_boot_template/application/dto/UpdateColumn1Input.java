package jp.spring_boot_template.application.dto;

import lombok.Builder;

@Builder
public record UpdateColumn1Input(long recordId, short column1) {}
