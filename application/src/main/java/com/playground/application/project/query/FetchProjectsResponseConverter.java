package com.playground.application.project.query;

import com.playground.presentation.controller.project.response.FetchProjectsResponse;
import com.playground.presentation.controller.project.response.FetchProjectsResponseProjectElement;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public final class FetchProjectsResponseConverter {
  public FetchProjectsResponse convertFetchProjectsResponse(
      final List<FindProjectsByParticipatingAccountIdDto>
          findProjectsByParticipatingAccountIdDtos) {
    final List<FetchProjectsResponseProjectElement> fetchProjectsResponseElements =
        findProjectsByParticipatingAccountIdDtos.stream()
            .map(
                findProjectsByParticipatingAccountIdDto -> {
                  final String projectId = findProjectsByParticipatingAccountIdDto.projectId();
                  final String projectName = findProjectsByParticipatingAccountIdDto.projectName();
                  return new FetchProjectsResponseProjectElement(projectId, projectName);
                })
            .collect(Collectors.toList());
    return new FetchProjectsResponse(fetchProjectsResponseElements);
  }
}
