package com.spring_boot_template.presentation.controller.project.converter;

import com.spring_boot_template.application.project.query.ProjectQueryDto;
import com.spring_boot_template.presentation.controller.project.response.ProjectResponse;
import com.spring_boot_template.presentation.controller.project.response.ProjectResponses;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public final class ProjectResponsesConverter {
    public ProjectResponses execute(final List<ProjectQueryDto> projectQueryDtos) {
        final List<ProjectResponse> projectResponses =
                projectQueryDtos.stream()
                        .map(
                                projectQueryDto -> {
                                    final String projectId = projectQueryDto.getProjectId();
                                    final String projectName = projectQueryDto.getProjectName();

                                    return new ProjectResponse(projectId, projectName);
                                })
                        .toList();

        return new ProjectResponses(projectResponses);
    }
}
