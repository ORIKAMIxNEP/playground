package com.playground.domain.model.account;

import com.playground.domain.model.account.value.AccountId;
import java.util.List;

public interface AccountRepository {
  void saveAccount(final Account account);

  List<Account> findAllAccount();

  Account findAccountByAccountId(final AccountId accountId);

  void deleteAccount(final AccountId accountId);
}
