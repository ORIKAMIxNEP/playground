package com.playground.infrastructure.account;

import static com.playground.jooq.Tables.ACCOUNTS;

import com.playground.domain.model.account.value.AccountId;
import com.playground.domain.model.account.value.AccountName;
import com.playground.domain.model.account.value.Password;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
final class AccountMapperImpl implements AccountMapper {
  private final DSLContext dslContext;

  @Override
  public void insertAccount(
      final AccountId accountId, final AccountName accountName, final Password password) {
    dslContext
        .insertInto(ACCOUNTS)
        .columns(ACCOUNTS.ACCOUNT_ID, ACCOUNTS.ACCOUNT_NAME, ACCOUNTS.PASSWORD)
        .values(accountId.value(), accountName.value(), password.value())
        .onConflict(ACCOUNTS.ACCOUNT_ID)
        .doUpdate()
        .set(ACCOUNTS.ACCOUNT_NAME, accountName.value())
        .set(ACCOUNTS.PASSWORD, password.value())
        .execute();
  }

  @Override
  public Optional<AccountDto> selectAccountByAccountId(final AccountId accountId) {
    return Optional.ofNullable(
        dslContext
            .select(ACCOUNTS.ACCOUNT_NAME)
            .from(ACCOUNTS)
            .where(ACCOUNTS.ACCOUNT_ID.eq(accountId.value()))
            .forUpdate()
            .fetchOneInto(AccountDto.class));
  }

  @Override
  public void deleteAccount(final AccountId accountId) {
    dslContext.deleteFrom(ACCOUNTS).where(ACCOUNTS.ACCOUNT_ID.eq(accountId.value())).execute();
  }
}
