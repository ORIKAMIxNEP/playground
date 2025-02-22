package com.playground.infrastructure.account;

import com.playground.application.account.query.AccountQueryService;
import com.playground.application.account.query.FindAccountIdByAccountNameAndPasswordDto;
import com.playground.domain.exception.ResourceNotFoundException;
import com.playground.domain.model.account.Account;
import com.playground.domain.model.account.value.AccountName;
import com.playground.domain.model.account.value.Password;
import com.playground.shared.constants.MessageCode;
import com.playground.shared.module.MessageGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
final class AccountQueryServiceImpl implements AccountQueryService {
  private final AccountQueryMapper accountQueryMapper;
  private final MessageGenerator messageGenerator;

  @Override
  public FindAccountIdByAccountNameAndPasswordDto findAccountIdByAccountNameAndPassword(
      final AccountName accountName, final Password password) {
    final FindAccountIdByAccountNameAndPasswordDto findAccountIdByAccountNameAndPasswordDto =
        accountQueryMapper
            .selectAccountIdByAccountNameAndPassword(accountName, password)
            .orElseThrow(
                () -> {
                  final String message =
                      messageGenerator.generateMessage(MessageCode.NOT_FOUND, Account.class);
                  throw new ResourceNotFoundException(message);
                });
    return findAccountIdByAccountNameAndPasswordDto;
  }
}
