package com.spring_boot_template.infrastructure.project.query;

import static com.spring_boot_template.jooq.tables.ProjectAccountParticipations.PROJECT_ACCOUNT_PARTICIPATIONS;
import static com.spring_boot_template.jooq.tables.Projects.PROJECTS;

import com.spring_boot_template.application.project.query.ProjectQueryService;
import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.presentation.controller.project.response.FetchProjectsResponse;
import com.spring_boot_template.presentation.controller.project.response.ProjectResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
final class ProjectQueryServiceImpl implements ProjectQueryService {
    private final DSLContext dslContext;

    @Override
    public FetchProjectsResponse findProjectsByAccountId(final AccountId accountId) {
        List<ProjectResponse> projectResponses = selectProjectsByAccountId(accountId.value());

        return new FetchProjectsResponse(projectResponses);
    }

    private List<ProjectResponse> selectProjectsByAccountId(final String accountId) {
        return dslContext
                .select(PROJECTS.PROJECT_ID, PROJECTS.PROJECT_NAME)
                .from(PROJECTS)
                .join(PROJECT_ACCOUNT_PARTICIPATIONS)
                .on(PROJECTS.PROJECT_ID.eq(PROJECT_ACCOUNT_PARTICIPATIONS.PROJECT_ID))
                .where(PROJECT_ACCOUNT_PARTICIPATIONS.ACCOUNT_ID.eq(accountId))
                .fetchInto(ProjectResponse.class);
    }
}
