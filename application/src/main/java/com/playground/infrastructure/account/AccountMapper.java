package com.playground.infrastructure.account;

import com.playground.domain.model.account.value.AccountId;
import com.playground.domain.model.account.value.AccountName;
import com.playground.domain.model.account.value.Password;
import java.util.Optional;

interface AccountMapper {
  void insertAccount(
      final AccountId accountId, final AccountName accountName, final Password password);

  Optional<AccountDto> selectAccountByAccountId(final AccountId accountId);

  void deleteAccount(final AccountId accountId);
}
