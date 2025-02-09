package com.playground.domain.model.account;

import com.playground.domain.model.account.value.AccountId;

public interface AccountRepository {
  void saveAccount(final Account account);

  Account findAccountByAccountId(final AccountId accountId);

  void deleteAccount(final AccountId accountId);
}
