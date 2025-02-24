package com.playground.domain.model.account;

import com.playground.domain.exception.DomainRuleViolationException;
import com.playground.domain.model.account.value.AccountId;
import com.playground.domain.model.account.value.AccountName;
import com.playground.domain.model.account.value.Password;
import com.playground.domain.module.IdGenerator;
import com.playground.domain.service.AccountNameExistenceChecker;
import com.playground.shared.constants.MessageCode;
import com.playground.shared.module.MessageGenerator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public final class Account {
  private final AccountId accountId;
  private AccountName accountName;
  private Password password;

  public static Account createAccount(
      final IdGenerator idGenerator, final AccountName accountName, final Password password) {
    final AccountId accountId = new AccountId(idGenerator.generateId());
    return new Account(accountId, accountName, password);
  }

  public static Account reconstructAccount(
      final AccountId accountId, final AccountName accountName) {
    return new Account(accountId, accountName, null);
  }

  public void updateAccount(
      final AccountName accountName,
      final Password password,
      final AccountNameExistenceChecker accountNameExistenceChecker,
      final MessageGenerator messageGenerator) {
    if (accountNameExistenceChecker.existsByAccountName(accountName)) {
      final String message = messageGenerator.generateMessage(MessageCode.NOT_FOUND, null);
      throw new DomainRuleViolationException(message);
    }
    this.accountName = accountName;
    this.password = password;
  }
}
