package com.spring_boot_template.infrastructure.project.query;

import com.spring_boot_template.application.project.query.ProjectQueryDto;
import com.spring_boot_template.application.project.query.ProjectQueryService;
import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.presentation.controller.project.response.FetchProjectsResponse;
import com.spring_boot_template.presentation.controller.project.response.ProjectResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
final class ProjectQueryServiceImpl implements ProjectQueryService {
    private final ProjectQueryMapper projectQueryMapper;

    @Override
    public FetchProjectsResponse findProjectsByAccountId(final AccountId accountId) {
        final List<ProjectQueryDto> projectQueryDtos =
                projectQueryMapper.selectProjectsByAccountId(accountId);

        final List<ProjectResponse> projectResponses =
                projectQueryDtos.stream()
                        .map(
                                projectQueryDto -> {
                                    final String projectId = projectQueryDto.getProjectId();
                                    final String projectName = projectQueryDto.getProjectName();

                                    return new ProjectResponse(projectId, projectName);
                                })
                        .toList();

        return new FetchProjectsResponse(projectResponses);
    }
}
