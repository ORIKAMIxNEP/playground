package jp.spring_boot_template.application.dto;

import lombok.Builder;

@Builder
public record AddInput(short column1, String column2) {}
