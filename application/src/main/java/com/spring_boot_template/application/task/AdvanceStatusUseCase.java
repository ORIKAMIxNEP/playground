package com.spring_boot_template.application.task;

public interface AdvanceStatusUseCase {
    void execute(final String projectIdRequest, final String taskIdRequest);
}
