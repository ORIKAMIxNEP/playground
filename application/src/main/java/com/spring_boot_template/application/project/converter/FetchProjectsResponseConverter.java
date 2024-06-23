package com.spring_boot_template.application.project.converter;

import com.spring_boot_template.application.project.query.dto.ProjectQueryDto;
import com.spring_boot_template.presentation.controller.project.response.FetchProjectsResponse;
import com.spring_boot_template.presentation.controller.project.response.ProjectResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public final class FetchProjectsResponseConverter {
    public FetchProjectsResponse execute(final ArrayList<ProjectQueryDto> projectQueryDtos) {
        final List<ProjectResponse> projectResponses =
                projectQueryDtos.stream()
                        .map(
                                projectQueryDto -> {
                                    final String projectId =
                                            projectQueryDto.getProjectId().getValue();
                                    final String projectName =
                                            projectQueryDto.getProjectName().getValue();

                                    return new ProjectResponse(projectId, projectName);
                                })
                        .toList();

        return new FetchProjectsResponse(projectResponses);
    }
}
