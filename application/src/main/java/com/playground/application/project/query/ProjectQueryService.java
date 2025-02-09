package com.playground.application.project.query;

import com.playground.domain.model.account.value.AccountId;
import com.playground.presentation.controller.project.response.FetchProjectsResponse;

public interface ProjectQueryService {
  FetchProjectsResponse findProjectsByParticipatingAccountId(final AccountId partcipatingAccountId);
}
