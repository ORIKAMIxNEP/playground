package com.spring_boot_template.domain.model.task.value;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class PostponeCount {
    @Getter private final int value;
}
