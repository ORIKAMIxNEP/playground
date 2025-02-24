package com.playground.application.account;

import com.playground.presentation.controller.account.request.UpdateAccountRequest;
import com.playground.presentation.shared.dto.AuthenticatedAccountId;

public interface UpdateAccountUseCase {
  void execute(
      final AuthenticatedAccountId authenticatedAccountId,
      final UpdateAccountRequest updateAccountRequest);
}
