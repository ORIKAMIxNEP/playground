package com.playground.application.account.impl;

import com.playground.application.account.UpdateAccountUseCase;
import com.playground.domain.model.account.Account;
import com.playground.domain.model.account.AccountRepository;
import com.playground.domain.model.account.value.AccountId;
import com.playground.domain.model.account.value.AccountName;
import com.playground.domain.model.account.value.Password;
import com.playground.domain.service.AccountNameExistenceChecker;
import com.playground.presentation.controller.account.request.UpdateAccountRequest;
import com.playground.presentation.shared.dto.SessionAccountId;
import com.playground.shared.module.MessageGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class UpdateAccountUseCaseImpl implements UpdateAccountUseCase {
  private final AccountRepository accountRepository;
  private final AccountNameExistenceChecker accountNameExistenceChecker;
  private final MessageGenerator messageGenerator;

  @Override
  @Transactional
  public void execute(
      final SessionAccountId sessionAccountId, final UpdateAccountRequest updateAccountRequest) {
    final AccountId accountId = new AccountId(sessionAccountId.value());
    final Account account = accountRepository.findAccountByAccountId(accountId);
    final AccountName accountName = new AccountName(updateAccountRequest.accountName());
    final Password password = new Password(updateAccountRequest.password());
    account.updateAccount(accountName, password, accountNameExistenceChecker, messageGenerator);
  }
}
