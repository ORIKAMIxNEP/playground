package com.spring_boot_template.domain.model.task.value;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TaskStatusValue {
    UNDONE(true),
    DOING(true),
    DONE(false);

    @Getter private final boolean isCountableAsTask;

    public final TaskStatusValue nextTaskStatus() {
        return switch (this) {
            case UNDONE -> DOING;
            case DOING -> DONE;
            default -> null;
        };
    }
}
