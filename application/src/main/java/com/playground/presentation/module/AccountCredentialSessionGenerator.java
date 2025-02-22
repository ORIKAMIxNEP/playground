package com.playground.presentation.module;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

@Component
public final class AccountCredentialSessionGenerator {
  public void generateAccountCredentialSession(
      final HttpServletRequest httpServletRequest, final String accountId) {
    final HttpSession httpSession = httpServletRequest.getSession();
    httpSession.setAttribute("AccountId", accountId);
  }
}
