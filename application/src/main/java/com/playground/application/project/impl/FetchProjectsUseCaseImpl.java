package com.playground.application.project.impl;

import com.playground.application.project.FetchProjectsUseCase;
import com.playground.application.project.query.FetchProjectsResponseConverter;
import com.playground.application.project.query.FindProjectsByParticipatingAccountIdDto;
import com.playground.application.project.query.ProjectQueryService;
import com.playground.domain.model.account.value.AccountId;
import com.playground.presentation.controller.project.response.FetchProjectsResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FetchProjectsUseCaseImpl implements FetchProjectsUseCase {
  private final ProjectQueryService projectQueryService;
  private final FetchProjectsResponseConverter fetchProjectsResponseConverter;

  @Override
  @Transactional
  public FetchProjectsResponse execute(final String participatingAccountIdRequest) {
    final AccountId participatingAccountId = new AccountId(participatingAccountIdRequest);
    final List<FindProjectsByParticipatingAccountIdDto> findProjectsByParticipatingAccountIdDtos =
        projectQueryService.findProjectsByParticipatingAccountId(participatingAccountId);
    return fetchProjectsResponseConverter.convertFetchProjectsResponse(
        findProjectsByParticipatingAccountIdDtos);
  }
}
