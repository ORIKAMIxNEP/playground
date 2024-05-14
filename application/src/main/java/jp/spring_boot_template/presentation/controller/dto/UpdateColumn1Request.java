package jp.spring_boot_template.presentation.controller.dto;

import lombok.Builder;

@Builder
public record UpdateColumn1Request(long recordId, short column1) {}
