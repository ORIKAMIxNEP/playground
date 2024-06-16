package com.spring_boot_template.domain.model.project.task.value;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Status {
    UNDONE(true),
    DOING(true),
    DONE(false);

    @Getter private final boolean isCountableAsTask;

    public final Status nextStatus() {
        return switch (this) {
            case UNDONE -> DOING;
            case DOING -> DONE;
            default -> null;
        };
    }
}
