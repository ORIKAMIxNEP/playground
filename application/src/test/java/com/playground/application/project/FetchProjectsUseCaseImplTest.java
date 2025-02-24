package com.playground.application.project;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.doReturn;

import com.playground.application.project.impl.FetchProjectsUseCaseImpl;
import com.playground.application.project.query.FetchProjectsResponseConverter;
import com.playground.application.project.query.FindProjectsByParticipatingAccountIdDto;
import com.playground.application.project.query.ProjectQueryService;
import com.playground.domain.model.account.value.AccountId;
import com.playground.presentation.controller.project.response.FetchProjectsResponse;
import com.playground.presentation.controller.project.response.FetchProjectsResponseProjectElement;
import com.playground.presentation.shared.dto.SessionAccountId;
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
  @Mock private FetchProjectsResponseConverter fetchProjectsResponseConverter;
  @InjectMocks private FetchProjectsUseCaseImpl fetchProjectsUseCaseImpl;

  @Test
  void testExecute() {
    final AccountId accountId = new AccountId("0000ABCDEFGHJKMNPQRSTVWXYZ");
    final List<FindProjectsByParticipatingAccountIdDto> findProjectsByParticipatingAccountIdDtos =
        generateFindProjectsByParticipatingAccountIdDtos();
    doReturn(findProjectsByParticipatingAccountIdDtos)
        .when(projectQueryService)
        .findProjectsByParticipatingAccountId(accountId);

    final FetchProjectsResponse expectedFetchProjectsResponse = generateFetchProjectsResponse();
    doReturn(expectedFetchProjectsResponse)
        .when(fetchProjectsResponseConverter)
        .convertFetchProjectsResponse(findProjectsByParticipatingAccountIdDtos);

    final SessionAccountId sessionAccountId = new SessionAccountId("0000ABCDEFGHJKMNPQRSTVWXYZ");
    final FetchProjectsResponse actualFetchProjectsResponse =
        fetchProjectsUseCaseImpl.execute(sessionAccountId);

    assertThat(actualFetchProjectsResponse).isEqualTo(expectedFetchProjectsResponse);
  }

  private List<FindProjectsByParticipatingAccountIdDto>
      generateFindProjectsByParticipatingAccountIdDtos() {
    final String projectId = "0100ABCDEFGHJKMNPQRSTVWXYZ";
    final String projectName = "ProjectName";
    final FindProjectsByParticipatingAccountIdDto findProjectsByParticipatingAccountIdDto =
        new FindProjectsByParticipatingAccountIdDto(projectId, projectName);
    return List.of(findProjectsByParticipatingAccountIdDto);
  }

  private FetchProjectsResponse generateFetchProjectsResponse() {
    final String projectId = "0100ABCDEFGHJKMNPQRSTVWXYZ";
    final String projectName = "ProjectName";
    final FetchProjectsResponseProjectElement fetchProjectsResponseProjectElement =
        new FetchProjectsResponseProjectElement(projectId, projectName);
    final List<FetchProjectsResponseProjectElement> fetchProjectsResponseProjectElements =
        List.of(fetchProjectsResponseProjectElement);
    return new FetchProjectsResponse(fetchProjectsResponseProjectElements);
  }
}
