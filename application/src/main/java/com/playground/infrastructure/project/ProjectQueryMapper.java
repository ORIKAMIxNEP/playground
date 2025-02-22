package com.playground.infrastructure.project;

import com.playground.application.project.query.FindProjectsByParticipatingAccountIdDto;
import com.playground.domain.model.account.value.AccountId;
import java.util.List;

interface ProjectQueryMapper {
  List<FindProjectsByParticipatingAccountIdDto> selectProjectsByParticipatingAccountId(
      final AccountId participatingAccountId);
}
