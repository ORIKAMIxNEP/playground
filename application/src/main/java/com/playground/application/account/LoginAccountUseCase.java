package com.playground.application.account;

import com.playground.presentation.authentication.request.LoginRequest;

public interface LoginAccountUseCase {
  String execute(final LoginRequest loginRequest);
}
