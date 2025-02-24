package com.playground.presentation.shared.module;

import com.playground.presentation.shared.dto.AuthenticatedAccountId;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.Collections;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Component;

@Component
public final class AuthenticationManager {
  public void registerAuthentication(
      final HttpServletRequest httpServletRequest, final String accountId, final String password) {
    final HttpSession httpSession = httpServletRequest.getSession(true);
    final SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
    final Authentication authentication =
        new UsernamePasswordAuthenticationToken(accountId, password, Collections.emptyList());
    securityContext.setAuthentication(authentication);
    httpSession.setAttribute(
        HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);
  }

  public AuthenticatedAccountId fetchAccountId() {
    final SecurityContext securityContext = SecurityContextHolder.getContext();
    final Authentication authentication = securityContext.getAuthentication();
    final String accountId = authentication.getName();
    return new AuthenticatedAccountId(accountId);
  }

  public void clearAuthentication(final HttpServletRequest httpServletRequest) {
    SecurityContextHolder.clearContext();
    httpServletRequest.getSession().invalidate();
  }
}
