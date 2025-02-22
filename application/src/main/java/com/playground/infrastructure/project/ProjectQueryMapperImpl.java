package com.playground.infrastructure.project;

import static com.playground.jooq.Tables.PROJECTS;
import static com.playground.jooq.Tables.PROJECT_ACCOUNT_PARTICIPATIONS;

import com.playground.application.project.query.FindProjectsByParticipatingAccountIdDto;
import com.playground.domain.model.account.value.AccountId;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
final class ProjectQueryMapperImpl implements ProjectQueryMapper {
  private final DSLContext dslContext;

  @Override
  public List<FindProjectsByParticipatingAccountIdDto> selectProjectsByParticipatingAccountId(
      final AccountId participatingAccountId) {
    return dslContext
        .select(PROJECTS.PROJECT_ID, PROJECTS.PROJECT_NAME)
        .from(PROJECTS)
        .join(PROJECT_ACCOUNT_PARTICIPATIONS)
        .on(PROJECTS.PROJECT_ID.eq(PROJECT_ACCOUNT_PARTICIPATIONS.PROJECT_ID))
        .where(
            PROJECT_ACCOUNT_PARTICIPATIONS.PARTICIPATING_ACCOUNT_ID.eq(
                participatingAccountId.value()))
        .fetchInto(FindProjectsByParticipatingAccountIdDto.class);
  }
}
