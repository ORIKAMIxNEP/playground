package com.spring_boot_template.application.project;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.doReturn;

import com.spring_boot_template.application.project.impl.FetchProjectsUseCaseImpl;
import com.spring_boot_template.application.project.query.ProjectQueryService;
import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.presentation.controller.project.response.FetchProjectsResponse;
import com.spring_boot_template.presentation.controller.project.response.FetchProjectsResponseProjectElement;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@RequiredArgsConstructor
@ExtendWith(MockitoExtension.class)
final class FetchProjectsUseCaseImplTest {
    @Mock private ProjectQueryService projectQueryService;
    @InjectMocks private FetchProjectsUseCaseImpl fetchProjectsUseCaseImpl;

    @Test
    void testExecute() {
        final AccountId participatingAccountId = new AccountId("0000ABCDEFGHJKMNPQRSTVWXYZ");
        final FetchProjectsResponse expectedFetchProjectsResponse = generateFetchProjectsResponse();
        doReturn(expectedFetchProjectsResponse)
                .when(projectQueryService)
                .findProjectsByParticipatingAccountId(participatingAccountId);

        final String participatingAccountIdRequest = "0000ABCDEFGHJKMNPQRSTVWXYZ";
        final FetchProjectsResponse actualFetchProjectsResponse =
                fetchProjectsUseCaseImpl.execute(participatingAccountIdRequest);

        assertThat(actualFetchProjectsResponse).isEqualTo(expectedFetchProjectsResponse);
    }

    private FetchProjectsResponse generateFetchProjectsResponse() {
        final String projectId = "0100ABCDEFGHJKMNPQRSTVWXYZ";
        final String projectName = "ProjectName";
        final List<FetchProjectsResponseProjectElement> fetchProjectsResponseProjectElements =
                List.of(new FetchProjectsResponseProjectElement(projectId, projectName));
        return new FetchProjectsResponse(fetchProjectsResponseProjectElements);
    }
}
