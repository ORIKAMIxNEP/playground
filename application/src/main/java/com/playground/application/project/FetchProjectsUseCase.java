package com.playground.application.project;

import com.playground.presentation.controller.project.response.FetchProjectsResponse;
import com.playground.presentation.shared.dto.SessionAccountId;

public interface FetchProjectsUseCase {
  FetchProjectsResponse execute(final SessionAccountId sessionAccountId);
}
