package com.playground.infrastructure.account;

import com.playground.domain.exception.ResourceNotFoundException;
import com.playground.domain.model.account.Account;
import com.playground.domain.model.account.AccountRepository;
import com.playground.domain.model.account.value.AccountId;
import com.playground.domain.model.account.value.AccountName;
import com.playground.domain.model.account.value.Password;
import com.playground.shared.constants.MessageCode;
import com.playground.shared.module.MessageGenerator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class AccountRepositoryImpl implements AccountRepository {
  private final AccountMapper accountMapper;
  private final MessageGenerator messageGenerator;

  @Override
  public void saveAccount(final Account account) {
    final AccountId accountId = account.getAccountId();
    final AccountName accountName = account.getAccountName();
    final Password password = account.getPassword();
    accountMapper.insertAccount(accountId, accountName, password);
  }

  @Override
  public List<Account> findAllAccount() {
    final List<AccountDto> accountDtos = accountMapper.selectAllAccount();
    return accountDtos.stream()
        .map(
            accountDto -> {
              final AccountName accountName = accountDto.accountName();
              return Account.reconstructAccount(null, accountName);
            })
        .collect(Collectors.toList());
  }

  @Override
  public Account findAccountByAccountId(final AccountId accountId) {
    final AccountDto accountDto =
        accountMapper
            .selectAccountByAccountId(accountId)
            .orElseThrow(
                () -> {
                  final String message =
                      messageGenerator.generateMessage(MessageCode.NOT_FOUND, Account.class);
                  return new ResourceNotFoundException(message);
                });
    final AccountName accountName = accountDto.accountName();
    return Account.reconstructAccount(accountId, accountName);
  }

  @Override
  public void deleteAccount(final AccountId accountId) {
    accountMapper.deleteAccount(accountId);
  }
}
