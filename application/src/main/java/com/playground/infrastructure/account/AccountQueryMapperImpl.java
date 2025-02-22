package com.playground.infrastructure.account;

import static com.playground.jooq.Tables.ACCOUNTS;

import com.playground.application.account.query.FindAccountIdByAccountNameAndPasswordDto;
import com.playground.domain.model.account.value.AccountName;
import com.playground.domain.model.account.value.Password;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
final class AccountQueryMapperImpl implements AccountQueryMapper {
  private final DSLContext dslContext;

  @Override
  public Optional<FindAccountIdByAccountNameAndPasswordDto> selectAccountIdByAccountNameAndPassword(
      final AccountName accountName, Password password) {
    return Optional.ofNullable(
        dslContext
            .select(ACCOUNTS.ACCOUNT_ID)
            .from(ACCOUNTS)
            .where(ACCOUNTS.ACCOUNT_NAME.eq(accountName.value()))
            .and(ACCOUNTS.PASSWORD.eq(password.value()))
            .fetchOneInto(FindAccountIdByAccountNameAndPasswordDto.class));
  }
}
