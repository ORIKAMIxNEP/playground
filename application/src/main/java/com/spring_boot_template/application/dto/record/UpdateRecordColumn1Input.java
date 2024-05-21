package com.spring_boot_template.application.dto.record;

import lombok.Builder;

@Builder
public record UpdateRecordColumn1Input(long recordId, byte column1) {}
