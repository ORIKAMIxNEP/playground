package com.playground.application.project;

import com.playground.presentation.controller.project.response.FetchProjectsResponse;

public interface FetchProjectsUseCase {
  FetchProjectsResponse execute(final String participatingAccountIdRequest);
}
