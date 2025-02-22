package com.playground.application.account.impl;

import com.playground.application.account.LoginAccountUseCase;
import com.playground.application.account.query.AccountQueryService;
import com.playground.application.account.query.FindAccountIdByAccountNameAndPasswordDto;
import com.playground.domain.model.account.value.AccountName;
import com.playground.domain.model.account.value.Password;
import com.playground.presentation.controller.account.request.LoginAccountRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class LoginAccountUseCaseImpl implements LoginAccountUseCase {
  private final AccountQueryService accountQueryService;

  @Override
  @Transactional
  public String execute(final LoginAccountRequest loginAccountRequest) {
    final AccountName accountName = new AccountName(loginAccountRequest.accountName());
    final Password password = new Password(loginAccountRequest.password());
    final FindAccountIdByAccountNameAndPasswordDto findAccountIdByAccountNameAndPasswordDto =
        accountQueryService.findAccountIdByAccountNameAndPassword(accountName, password);
    return findAccountIdByAccountNameAndPasswordDto.accountId();
  }
}
