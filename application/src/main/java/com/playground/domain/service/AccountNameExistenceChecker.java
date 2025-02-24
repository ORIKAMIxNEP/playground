package com.playground.domain.service;

import com.playground.domain.model.account.Account;
import com.playground.domain.model.account.AccountRepository;
import com.playground.domain.model.account.value.AccountName;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class AccountNameExistenceChecker {
  private final AccountRepository accountRepository;

  public boolean existsByAccountName(final AccountName accountName) {
    final List<Account> accounts = accountRepository.findAllAccount();
    return accounts.stream().anyMatch(account -> account.getAccountName().equals(accountName));
  }
}
