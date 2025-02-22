package com.playground.application.account.query;

import com.playground.domain.model.account.value.AccountName;
import com.playground.domain.model.account.value.Password;

public interface AccountQueryService {
  FindAccountIdByAccountNameAndPasswordDto findAccountIdByAccountNameAndPassword(
      final AccountName accountName, final Password password);
}
