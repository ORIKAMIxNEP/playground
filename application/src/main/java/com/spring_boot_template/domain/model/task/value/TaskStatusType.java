package com.spring_boot_template.domain.model.task.value;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TaskStatusType {
    UNDONE(true),
    DOING(true),
    DONE(false);

    @Getter private final boolean isCountableAsTask;

    public final TaskStatusType nextTaskStatus() {
        return switch (this) {
            case UNDONE -> DOING;
            case DOING -> DONE;
            default -> null;
        };
    }
}
