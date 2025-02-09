package com.playground.application.project.impl;

import com.playground.application.project.FetchProjectsUseCase;
import com.playground.application.project.query.ProjectQueryService;
import com.playground.domain.model.account.value.AccountId;
import com.playground.presentation.controller.project.response.FetchProjectsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FetchProjectsUseCaseImpl implements FetchProjectsUseCase {
  private final ProjectQueryService projectQueryService;

  @Override
  @Transactional
  public FetchProjectsResponse execute(final String participatingAccountIdRequest) {
    final AccountId participatingAccountId = new AccountId(participatingAccountIdRequest);
    return projectQueryService.findProjectsByParticipatingAccountId(participatingAccountId);
  }
}
