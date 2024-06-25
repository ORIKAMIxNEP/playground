package com.spring_boot_template.application.project.task;

public interface DeleteTaskUseCase {
    void execute(final String projectIdRequest, final String taskIdRequest);
}
