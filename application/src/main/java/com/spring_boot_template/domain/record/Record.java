package com.spring_boot_template.domain.record;

import lombok.Builder;

@Builder
public record Record(long recordId, byte column1, String column2) {}
