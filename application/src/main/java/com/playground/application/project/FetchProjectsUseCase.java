package com.playground.application.project;

import com.playground.presentation.controller.project.response.FetchProjectsResponse;
import com.playground.presentation.shared.dto.AuthenticatedAccountId;

public interface FetchProjectsUseCase {
  FetchProjectsResponse execute(final AuthenticatedAccountId authenticatedAccountId);
}
