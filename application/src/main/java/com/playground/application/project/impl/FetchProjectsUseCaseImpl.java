package com.playground.application.project.impl;

import com.playground.application.project.FetchProjectsUseCase;
import com.playground.application.project.query.FetchProjectsResponseConverter;
import com.playground.application.project.query.FindProjectsByParticipatingAccountIdDto;
import com.playground.application.project.query.ProjectQueryService;
import com.playground.domain.model.account.value.AccountId;
import com.playground.presentation.controller.project.response.FetchProjectsResponse;
import com.playground.presentation.shared.dto.SessionAccountId;
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
  public FetchProjectsResponse execute(final SessionAccountId sessionAccountId) {
    final AccountId accountId = new AccountId(sessionAccountId.value());
    final List<FindProjectsByParticipatingAccountIdDto> findProjectsByParticipatingAccountIdDtos =
        projectQueryService.findProjectsByParticipatingAccountId(accountId);
    return fetchProjectsResponseConverter.convertFetchProjectsResponse(
        findProjectsByParticipatingAccountIdDtos);
  }
}
