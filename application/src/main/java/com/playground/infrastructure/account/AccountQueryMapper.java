package com.playground.infrastructure.account;

import com.playground.application.account.query.FindAccountIdByAccountNameAndPasswordDto;
import com.playground.domain.model.account.value.AccountName;
import com.playground.domain.model.account.value.Password;
import java.util.Optional;

interface AccountQueryMapper {
  Optional<FindAccountIdByAccountNameAndPasswordDto> selectAccountIdByAccountNameAndPassword(
      final AccountName accountName, final Password password);
}
