package com.spring_boot_template.application.task;

import com.spring_boot_template.application.task.query.TaskQueryDto;

public interface FetchTaskUseCase {
    TaskQueryDto execute(final String projectIdRequest, final String taskIdRequest);
}
