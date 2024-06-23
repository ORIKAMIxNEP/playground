package com.spring_boot_template.application.project.converter;

import com.spring_boot_template.application.project.query.dto.ProjectQueryDto;
import com.spring_boot_template.presentation.controller.project.response.FetchProjectsResponse;
import com.spring_boot_template.presentation.controller.project.response.ProjectResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public final class FetchProjectsResponseConverter {
    public FetchProjectsResponse execute(final ArrayList<ProjectQueryDto> projectQueryDtos) {
        final List<ProjectResponse> projectResponses=projectQueryDtos.stream().map(projectQueryDto-> {
            final String projectId=projectQueryDto.projectId().getValue();
            final String projectName=projectQueryDto.projectName().getValue();

            return new ProjectResponse(projectId,projectName);
        }).toList();

        return new FetchProjectsResponse(projectResponses);
    }
}
