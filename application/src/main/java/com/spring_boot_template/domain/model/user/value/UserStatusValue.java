package com.spring_boot_template.domain.model.user.value;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum UserStatusValue {
    ACTIVE(true),
    DELETED(false);

    @Getter private final boolean isAssignableToTask;
}
