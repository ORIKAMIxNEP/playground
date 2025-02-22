package com.playground.application.project.query;

import com.playground.domain.model.account.value.AccountId;
import java.util.List;

public interface ProjectQueryService {
  List<FindProjectsByParticipatingAccountIdDto> findProjectsByParticipatingAccountId(
      final AccountId partcipatingAccountId);
}
