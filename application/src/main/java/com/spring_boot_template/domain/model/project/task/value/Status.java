package com.spring_boot_template.domain.model.project.task.value;

import java.util.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Status {
    UNDONE(true),
    DOING(true),
    DONE(false);

    private final boolean isCountableAsTask;

    public final Optional<Status> nextStatus() {
        return Optional.ofNullable(
                switch (this) {
                    case UNDONE -> DOING;
                    case DOING -> DONE;
                    default -> null;
                });
    }
}
