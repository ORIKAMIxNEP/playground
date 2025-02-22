package com.playground.application.account;

import com.playground.presentation.controller.account.request.LoginAccountRequest;

public interface LoginAccountUseCase {
  String execute(final LoginAccountRequest loginAccountRequest);
}
