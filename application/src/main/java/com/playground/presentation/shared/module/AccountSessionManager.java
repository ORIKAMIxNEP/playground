package com.playground.presentation.shared.module;

import com.playground.presentation.shared.dto.SessionAccountId;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

@Component
public final class AccountSessionManager {
  private static final String ACCOUNT_SESSION_KEY = "accountId";

  public void generateAccountSession(
      final HttpServletRequest httpServletRequest, final String accountId) {
    final HttpSession httpSession = httpServletRequest.getSession();
    httpSession.setAttribute(ACCOUNT_SESSION_KEY, accountId);
  }

  public SessionAccountId fetchAccountId(final HttpServletRequest httpServletRequest) {
    final HttpSession httpSession = httpServletRequest.getSession(false);
    final String accountId = httpSession.getAttribute(ACCOUNT_SESSION_KEY).toString();
    return new SessionAccountId(accountId);
  }
}
