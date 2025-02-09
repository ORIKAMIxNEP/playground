package com.spring_boot_template.infrastructure.project;

import static com.spring_boot_template.jooq.tables.ProjectAccountParticipations.PROJECT_ACCOUNT_PARTICIPATIONS;
import static com.spring_boot_template.jooq.tables.Projects.PROJECTS;

import com.spring_boot_template.application.project.query.FetchProjectsQueryDto;
import com.spring_boot_template.application.project.query.ProjectQueryService;
import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.presentation.controller.project.response.FetchProjectsResponse;
import com.spring_boot_template.presentation.controller.project.response.FetchProjectsResponseProjectElement;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
final class ProjectQueryServiceImpl implements ProjectQueryService {
    private final DSLContext dslContext;

    @Override
    public FetchProjectsResponse findProjectsByParticipatingAccountId(
            final AccountId participatingAccountId) {
        final List<FetchProjectsQueryDto> fetchProjectsQueryDtos =
                selectProjectsByParticipatingAccountId(participatingAccountId.value());
        final List<FetchProjectsResponseProjectElement> fetchProjectsResponseElements =
                fetchProjectsQueryDtos.stream()
                        .map(
                                fetchProjectsQueryDto -> {
                                    final String projectId = fetchProjectsQueryDto.projectId();
                                    final String projectName = fetchProjectsQueryDto.projectName();
                                    return new FetchProjectsResponseProjectElement(
                                            projectId, projectName);
                                })
                        .collect(Collectors.toList());
        return new FetchProjectsResponse(fetchProjectsResponseElements);
    }

    private List<FetchProjectsQueryDto> selectProjectsByParticipatingAccountId(
            final String participatingAccountId) {
        return dslContext
                .select(PROJECTS.PROJECT_ID, PROJECTS.PROJECT_NAME)
                .from(PROJECTS)
                .join(PROJECT_ACCOUNT_PARTICIPATIONS)
                .on(PROJECTS.PROJECT_ID.eq(PROJECT_ACCOUNT_PARTICIPATIONS.PROJECT_ID))
                .where(
                        PROJECT_ACCOUNT_PARTICIPATIONS.PARTICIPATING_ACCOUNT_ID.eq(
                                participatingAccountId))
                .fetchInto(FetchProjectsQueryDto.class);
    }
}
