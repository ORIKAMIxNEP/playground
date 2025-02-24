package com.playground.application.account;

import com.playground.presentation.controller.account.request.UpdateAccountRequest;
import com.playground.presentation.shared.dto.SessionAccountId;

public interface UpdateAccountUseCase {
  void execute(
      final SessionAccountId sessionAccountId, final UpdateAccountRequest updateAccountRequest);
}
