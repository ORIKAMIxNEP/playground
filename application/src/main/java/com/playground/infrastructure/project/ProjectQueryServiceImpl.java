package com.playground.infrastructure.project;

import com.playground.application.project.query.FindProjectsByParticipatingAccountIdDto;
import com.playground.application.project.query.ProjectQueryService;
import com.playground.domain.model.account.value.AccountId;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
final class ProjectQueryServiceImpl implements ProjectQueryService {
  private final ProjectQueryMapper projectQueryMapper;

  @Override
  public List<FindProjectsByParticipatingAccountIdDto> findProjectsByParticipatingAccountId(
      final AccountId participatingAccountId) {
    return projectQueryMapper.selectProjectsByParticipatingAccountId(participatingAccountId);
  }
}
