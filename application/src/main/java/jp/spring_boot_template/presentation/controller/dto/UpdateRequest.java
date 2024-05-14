package jp.spring_boot_template.presentation.controller.dto;

import lombok.Builder;

@Builder
public record UpdateRequest(long recordId, short column1, String column2) {}
