package com.spring_boot_template.application.project;

import com.spring_boot_template.application.project.query.ProjectQueryDto;
import java.util.List;

public interface FetchProjectsUseCase {
    List<ProjectQueryDto> execute();
}
